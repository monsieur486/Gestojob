package com.mr486.gestojob.controller.api;

import com.mr486.gestojob.model.Appointment;
import com.mr486.gestojob.model.Company;
import com.mr486.gestojob.model.PhoneCall;
import com.mr486.gestojob.model.Message;
import com.mr486.gestojob.service.CompanyService;
import com.mr486.gestojob.service.PhoneCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiPhoneCallController {

  private final PhoneCallService phoneCallService;

  @Autowired
  private CompanyService companyService;

  public ApiPhoneCallController(PhoneCallService phoneCallService) {
    this.phoneCallService = phoneCallService;
  }

  @GetMapping(value = "/phone_calls")
  public ResponseEntity<Object> allTelephone() {
    try {
      List<PhoneCall> result = phoneCallService.allPhoneCall();
      return Message.generateResponse(null, HttpStatus.OK, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @GetMapping("/phone_calls/{id}")
  public ResponseEntity<Object> getTelephone(@PathVariable Long id) {
    try {
      Optional<PhoneCall> result = Optional.ofNullable(phoneCallService.phoneCallById(id));

      if(result.isPresent()){
        return Message.generateResponse(null, HttpStatus.OK, result);
      } else {
        return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
      }
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @PostMapping("/companies/{id}/phone_calls")
  public ResponseEntity<Object> save(@RequestBody PhoneCall phoneCall, @PathVariable Long id) {

    if(Boolean.TRUE.equals(companyService.existe(id))){
      Company company = companyService.companyById(id);
      try {
        phoneCall.setCompany(company);
        PhoneCall result = phoneCallService.savePhoneCall(phoneCall);
        return Message.generateResponse("Phone call successfully created.", HttpStatus.CREATED, result);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Company not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }

  }

  @PutMapping("/phone_calls/{id}")
  public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody PhoneCall phoneCall) {
    try {
      PhoneCall result = phoneCallService.updatePhoneCall(id, phoneCall);
      return Message.generateResponse("Successfully updated", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/phone_calls/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if(Boolean.TRUE.equals(phoneCallService.existe(id))){
      try {
        phoneCallService.deletePhoneCallById(id);
        return Message.generateResponse("Entity deleted successfully", HttpStatus.ACCEPTED, null);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }
}
