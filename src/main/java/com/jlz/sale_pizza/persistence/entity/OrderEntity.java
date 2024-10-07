package com.jlz.sale_pizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_order", nullable = false)
  private Integer idOrder;

  @Column(name = "id_customer", nullable = false, length = 15)
  private String idCustomer;

  @Column(columnDefinition = "DATETIME", nullable = false)
  private LocalDateTime date;

  @Column(name = "total", nullable = false, columnDefinition = "DECIMAL(6,2)")
  private Double total;

  @Column(name = "method", nullable = false, columnDefinition = "CHAR(1)")
  private Character method;

  @Column(name = "additional_notes", length = 200)
  private String additionalNotes;

  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
  private List<OrderItemEntity> items;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
  @JsonIgnore
  private CustomerEntity customer;

}
