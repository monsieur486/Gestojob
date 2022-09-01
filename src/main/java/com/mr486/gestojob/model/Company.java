package com.mr486.gestojob.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "Company")
@Table(name = "company")
public class Company implements Serializable {

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

  @OneToMany(mappedBy="company", cascade = CascadeType.REMOVE)
  @OrderBy("mailDate DESC")
  @ToString.Exclude
  private List<Mail> emailsList;

  @OneToMany(mappedBy="company", cascade = CascadeType.REMOVE)
  @OrderBy("phoneCallDate DESC")
  @ToString.Exclude
  private List<PhoneCall> phoneCallsList;

  @OneToMany(mappedBy="company", cascade = CascadeType.REMOVE)
  @OrderBy("appointmentDate DESC")
  @ToString.Exclude
  private List<Appointment> appointmentsList;

  @Lob
  @Column(name="comment", length=512)
  private String comment;

  public Company(
    String name,
    String email,
    String telephone,
    String adress,
    String complement,
    String postalCode,
    String city,
    Boolean negative,
    String comment
  ){
    this.name = name;
    this.email = email;
    this.telephone = telephone;
    this.adress = adress;
    this.complement = complement;
    this.postalCode = postalCode;
    this.city = city;
    this.comment = comment;
    this.negative = negative;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Company company = (Company) o;
    return id != null && Objects.equals(id, company.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void setId(Long id) {
    this.id = id;
  }

}
