package com.mr486.gestojob.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Compagny implements Serializable {

  private static final long serialVersionUID = 4048798961366546485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 150, nullable = false)
  @NotBlank(message = "No empty field allowed")
  private String name;

  @Column(name = "email", length = 150)
  private String email;

  @Column(name = "telephone", length = 150)
  private String telephone;

  @Column(name = "adress", length = 150)
  private String adress;

  @Column(name = "complement", length = 150)
  private String complement;

  @Column(name = "postal_code", length = 150)
  private String postalCode;

  @Column(name = "city", length = 150)
  private String city;

  @Column(name = "negative" )
  private Boolean negative = false;

  @OneToMany(mappedBy="compagny")
  private List<Mail> emailList;

  @OneToMany(mappedBy="compagny")
  private List<Telephone> phoneCalls;

  @OneToMany(mappedBy="compagny")
  private List<Appointment> appointmentList;

  public Compagny(
    String name,
    String email,
    String telephone,
    String adress,
    String complement,
    String postalCode,
    String city
  ){
    this.name = name;
    this.email = email;
    this.telephone = telephone;
    this.adress = adress;
    this.complement = complement;
    this.postalCode = postalCode;
    this.city = city;
    this.negative = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Compagny compagny = (Compagny) o;
    return id != null && Objects.equals(id, compagny.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void setId(Long id) {
    this.id = id;
  }

}
