package com.mr486.gestojob.controller.api;

import com.mr486.gestojob.model.Company;
import com.mr486.gestojob.model.Mail;
import com.mr486.gestojob.model.Message;
import com.mr486.gestojob.service.CompanyService;
import com.mr486.gestojob.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiMailController {

  private final MailService mailService;

  @Autowired
  private CompanyService companyService;

  public ApiMailController(MailService mailService) {
    this.mailService = mailService;
  }

  @GetMapping(value = "/companies/{id}/emails")
  public ResponseEntity<Object> allMailsByCompanyId(@PathVariable Long id) {
    if(Boolean.TRUE.equals(companyService.existe(id))){
      try {
        List<Mail> result = mailService.mailsByCompanyId(id);
        return Message.generateResponse(null, HttpStatus.OK, result);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
      }
    } else {
      return Message.generateResponse(
        "Company not found with id: " + id.toString(),
        HttpStatus.NOT_FOUND,
        null
      );
    }
  }
  @GetMapping("/emails/{id}")
  public ResponseEntity<Object> getMail(@PathVariable Long id) {
    try {
      Optional<Mail> result = Optional.ofNullable(mailService.mailById(id));
      if(result.isPresent()){
        return Message.generateResponse(null, HttpStatus.OK, result);
      } else {
        return Message.generateResponse("Mail not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
      }
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @PostMapping("/companies/{id}/emails")
  public ResponseEntity<Object> save(@RequestBody Mail mail, @PathVariable Long id) {
    if(Boolean.TRUE.equals(companyService.existe(id))){
      Company company = companyService.companyById(id);
      try {
        mail.setCompany(company);
        Mail result = mailService.saveMail(mail);
        return Message.generateResponse("Email successfully created.", HttpStatus.CREATED, result);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Company not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }

  @PutMapping("/emails/{id}")
  public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Mail mail) {
    try {
      Mail result = mailService.updateMail(id, mail);
      return Message.generateResponse("Successfully updated", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/emails/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if(Boolean.TRUE.equals(mailService.existe(id))){
      try {
        mailService.deleteMailById(id);
        return Message.generateResponse("Entity deleted successfully", HttpStatus.ACCEPTED, null);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }
}
