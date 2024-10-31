package com.jlz.sale_pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {
  @Id
  @Column(name = "username", nullable = false)
  private String userName;

  @Id
  @Column(name = "role", nullable = false)
  private String role;

  @Column(name = "granted_date", columnDefinition = "DATETIME", nullable = false)
  private LocalDateTime grantedDate;

  @ManyToOne
  @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
  private UserEntity user;
}
