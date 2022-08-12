package com.mr486.gestojob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Telephone implements Serializable {

  private static final long serialVersionUID = 4048798961366546485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 150, nullable = false)
  @NotBlank(message = "No empty field allowed")
  private String name;

  @Basic
  @Column(name = "telephone_date")
  private java.sql.Date telephoneDate;

  @Basic
  @Column(name = "telephone_time")
  private java.sql.Time telephoneTime;

  @JsonIgnore
  @ManyToOne
  @JoinColumn( name="compagny_id", nullable=false )
  private Compagny compagny;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Telephone telephone = (Telephone) o;
    return id != null && Objects.equals(id, telephone.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void setId(Long id) {
    this.id = id;
  }

}
