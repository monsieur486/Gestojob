package com.mr486.gestojob.controller.api;

import com.mr486.gestojob.dto.CompanyDto;
import com.mr486.gestojob.model.Company;
import com.mr486.gestojob.model.Message;
import com.mr486.gestojob.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiCompanyController {

  @Autowired
  private CompanyService companyService;

  public ApiCompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping(value = "/companies")
  public ResponseEntity<Object> allCompany(@RequestParam(required = false) String name) {
    try {
      List<Company> result = new ArrayList<>();
      if(name == null){
        result.addAll(companyService.allCompany());
      } else {
        result.addAll(companyService.findByNameContaining(name));
      }
      return Message.generateResponse(null, HttpStatus.OK, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @GetMapping("/companies/{id}")
  public ResponseEntity<Object> getCompany(@PathVariable Long id) {
    try {
      Optional<Company> result = Optional.ofNullable(companyService.companyById(id));

      if(result.isPresent()){
        return Message.generateResponse(null, HttpStatus.OK, result);
      } else {
        return Message.generateResponse("Company not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
      }
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @PostMapping("/companies")
  public ResponseEntity<Object> save(@RequestBody CompanyDto company) {
    try {
      Company result = companyService.saveCompany(company);
      return Message.generateResponse("Company successfully created.", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @PutMapping("/companies/{id}")
  public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Company company) {
    try {
      Company result = companyService.updateCompany(id, company);
      return Message.generateResponse("Company successfully updated", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/companies/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if(Boolean.TRUE.equals(companyService.existe(id))){
      try {
        companyService.deleteCompanyById(id);
        return Message.generateResponse("Company deleted successfully", HttpStatus.ACCEPTED, null);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Company not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }

}
