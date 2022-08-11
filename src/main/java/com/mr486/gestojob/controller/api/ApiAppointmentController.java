package com.mr486.gestojob.controller.api;

import com.mr486.gestojob.model.Appointment;
import com.mr486.gestojob.model.Message;
import com.mr486.gestojob.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiAppointmentController {

  private final AppointmentService appointmentService;

  public ApiAppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping(value = "/appointments")
  public ResponseEntity<Object> allAppointment() {
    try {
      List<Appointment> result = appointmentService.allAppointment();
      return Message.generateResponse(null, HttpStatus.OK, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @GetMapping("/appointments/{id}")
  public ResponseEntity<Object> getAppointment(@PathVariable Long id) {
    try {
      Optional<Appointment> result = Optional.ofNullable(appointmentService.appointmentById(id));

      if(result.isPresent()){
        return Message.generateResponse(null, HttpStatus.OK, result);
      } else {
        return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
      }
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @PostMapping("/appointments")
  public ResponseEntity<Object> save(@RequestBody Appointment appointment) {
    try {
      Appointment result = appointmentService.saveAppointment(appointment);
      return Message.generateResponse("Entity successfully created.", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @PutMapping("/appointments/{id}")
  public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Appointment appointment) {
    try {
      Appointment result = appointmentService.updateAppointment(id, appointment);
      return Message.generateResponse("Successfully updated", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/appointments/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if(Boolean.TRUE.equals(appointmentService.existe(id))){
      try {
        appointmentService.deleteAppointmentById(id);
        return Message.generateResponse("Entity deleted successfully", HttpStatus.ACCEPTED, null);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }
}
