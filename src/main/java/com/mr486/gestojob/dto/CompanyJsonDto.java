package com.mr486.gestojob.dto;

import com.mr486.gestojob.model.Appointment;
import com.mr486.gestojob.model.Mail;
import com.mr486.gestojob.model.PhoneCall;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.mr486.gestojob.model.Company} entity
 */
@Data
public class CompanyJsonDto implements Serializable {
  private final Long id;
  @NotBlank(message = "No empty field allowed")
  private final String name;
  private final String email;
  private final String telephone;
  private final String adress;
  private final String complement;
  private final String postalCode;
  private final String city;
  private final Boolean negative;
  private final List<Mail> emailsList;
  private final List<PhoneCall> phoneCallsList;
  private final List<Appointment> appointmentsList;
  private final String comment;
}
