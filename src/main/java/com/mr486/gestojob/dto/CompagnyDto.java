package com.mr486.gestojob.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class CompagnyDto {
  private String name;
  private String email;
  private String telephone;
  private String adress;
  private String complement;
  private String postalCode;
  private String city;
  private Boolean negative = false;
}
