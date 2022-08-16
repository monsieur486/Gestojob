package com.mr486.gestojob.service;

import com.mr486.gestojob.model.Appointment;
import com.mr486.gestojob.model.Mail;
import com.mr486.gestojob.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  public Boolean existe(Long id) {
    return appointmentRepository.existsById(id);
  }

  public Appointment appointmentById(Long id) {
    return appointmentRepository.findById(id).orElse(null);
  }

  public List<Appointment> appointmentsByCompanyId(Long id) {
    List<Appointment> appointments;
    appointments = appointmentRepository.findByCompanyIdOrderByAppointmentDateDesc(id);
    return appointments;
  }

  public Appointment saveAppointment(Appointment appointment) {
    return appointmentRepository.save(appointment);
  }

  public Appointment updateAppointment(Long id, Appointment appointment) {
    appointment.setId(id);
    return appointmentRepository.save(appointment);
  }

  public void deleteAppointmentById(Long id) {
    this.appointmentRepository.deleteById(id);
  }

  public Long count() {
    return appointmentRepository.count();
  }

}
