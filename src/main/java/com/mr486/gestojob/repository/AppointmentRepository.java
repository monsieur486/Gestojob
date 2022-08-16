package com.mr486.gestojob.repository;

import com.mr486.gestojob.model.Appointment;
import com.mr486.gestojob.model.PhoneCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  List<Appointment> findByCompanyIdOrderByAppointmentDateDesc(Long id);
}
