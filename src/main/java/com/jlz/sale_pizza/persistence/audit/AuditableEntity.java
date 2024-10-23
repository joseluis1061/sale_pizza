package com.jlz.sale_pizza.persistence.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;
@MappedSuperclass
public class AuditableEntity {

  @Column(name = "created_date", updatable = false)
  @CreatedDate
  @JsonIgnore
  private LocalDate createdDate;

  @Column(name = "modified_date")
  @LastModifiedDate
  @JsonIgnore
  private LocalDate modifiedDate;
}
