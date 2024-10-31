package com.jlz.sale_pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
  @Id
  @Column(name = "username", nullable = false, length = 20)
  private String username;

  @Column(name = "password", nullable = false, length = 200)
  private String password;

  @Column(name = "email", nullable = false, length = 50)
  private String email;

  @Column(name = "locked", columnDefinition = "TINYINT")
  private Boolean locked;

  @Column(name = "disabled", columnDefinition = "TINYINT")
  private Boolean disabled;

  @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
  private List<UserRoleEntity> roles;
}
