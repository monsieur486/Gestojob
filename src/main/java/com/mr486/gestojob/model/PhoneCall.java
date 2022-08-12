package com.mr486.gestojob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "phone_call")
public class PhoneCall implements Serializable {

  private static final long serialVersionUID = 4048798961366546485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic
  @Column(name = "phone_call_date")
  private java.sql.Date phoneCallDate;

  @Basic
  @Column(name = "phone_call_time")
  private java.sql.Time phoneCallTime;

  @JsonIgnore
  @ManyToOne
  @JoinColumn( name="company_id", nullable=false )
  private Company company;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "app_object")
  private AppObject appObject;

  @Lob
  @Column(name="comment", length=512)
  private String comment;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    PhoneCall phoneCall = (PhoneCall) o;
    return id != null && Objects.equals(id, phoneCall.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void setId(Long id) {
    this.id = id;
  }

}
