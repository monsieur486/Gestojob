package com.mr486.gestojob.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class CompanyDto {
  private String name;
  private String email;
  private String telephone;
  private String adress;
  private String complement;
  private String postalCode;
  private String city;
  private String comment;
  private Boolean negative = false;
}
