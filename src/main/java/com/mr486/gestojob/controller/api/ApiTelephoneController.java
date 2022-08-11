package com.mr486.gestojob.controller.api;

import com.mr486.gestojob.model.Telephone;
import com.mr486.gestojob.model.Message;
import com.mr486.gestojob.service.TelephoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiTelephoneController {

  private final TelephoneService telephoneService;

  public ApiTelephoneController(TelephoneService telephoneService) {
    this.telephoneService = telephoneService;
  }

  @GetMapping(value = "/telephones")
  public ResponseEntity<Object> allTelephone() {
    try {
      List<Telephone> result = telephoneService.allTelephone();
      return Message.generateResponse(null, HttpStatus.OK, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @GetMapping("/telephones/{id}")
  public ResponseEntity<Object> getTelephone(@PathVariable Long id) {
    try {
      Optional<Telephone> result = Optional.ofNullable(telephoneService.telephoneById(id));

      if(result.isPresent()){
        return Message.generateResponse(null, HttpStatus.OK, result);
      } else {
        return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
      }
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @PostMapping("/telephones")
  public ResponseEntity<Object> save(@RequestBody Telephone telephone) {
    try {
      Telephone result = telephoneService.saveTelephone(telephone);
      return Message.generateResponse("Entity successfully created.", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @PutMapping("/telephones/{id}")
  public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Telephone telephone) {
    try {
      Telephone result = telephoneService.updateTelephone(id, telephone);
      return Message.generateResponse("Successfully updated", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/telephones/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if(Boolean.TRUE.equals(telephoneService.existe(id))){
      try {
        telephoneService.deleteTelephoneById(id);
        return Message.generateResponse("Entity deleted successfully", HttpStatus.ACCEPTED, null);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }
}
