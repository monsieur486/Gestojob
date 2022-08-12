package com.mr486.gestojob.controller.api;

import com.mr486.gestojob.dto.CompagnyDto;
import com.mr486.gestojob.model.Compagny;
import com.mr486.gestojob.model.Message;
import com.mr486.gestojob.service.CompagnyService;
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
public class ApiCompagnyController {

  @Autowired
  private CompagnyService compagnyService;

  public ApiCompagnyController(CompagnyService compagnyService) {
    this.compagnyService = compagnyService;
  }

  @GetMapping(value = "/compagnies")
  public ResponseEntity<Object> allCompagny() {
    try {
      List<Compagny> result = compagnyService.allCompagny();
      return Message.generateResponse(null, HttpStatus.OK, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @GetMapping("/compagnies/{id}")
  public ResponseEntity<Object> getCompagny(@PathVariable Long id) {
    try {
      Optional<Compagny> result = Optional.ofNullable(compagnyService.compagnyById(id));

      if(result.isPresent()){
        return Message.generateResponse(null, HttpStatus.OK, result);
      } else {
        return Message.generateResponse("Compagny not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
      }
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @PostMapping("/compagnies")
  public ResponseEntity<Object> save(@RequestBody CompagnyDto compagny) {
    try {
      Compagny result = compagnyService.saveCompagny(compagny);
      return Message.generateResponse("Compagny successfully created.", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @PutMapping("/compagnies/{id}")
  public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Compagny compagny) {
    try {
      Compagny result = compagnyService.updateCompagny(id, compagny);
      return Message.generateResponse("Compagny successfully updated", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/compagnies/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if(Boolean.TRUE.equals(compagnyService.existe(id))){
      try {
        compagnyService.deleteCompagnyById(id);
        return Message.generateResponse("Compagny deleted successfully", HttpStatus.ACCEPTED, null);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Compagny not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }

}
