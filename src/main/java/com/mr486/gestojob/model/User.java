package com.mr486.gestojob.model;

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
public class User implements Serializable {

  private static final long serialVersionUID = 4048798961366546485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 150, nullable = false)
  @NotBlank(message = "No empty field allowed")
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    User user = (User) o;
    return id != null && Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void setId(Long id) {
    this.id = id;
  }

}
