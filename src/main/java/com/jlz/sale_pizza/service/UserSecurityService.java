package com.jlz.sale_pizza.service;

import com.jlz.sale_pizza.persistence.entity.UserEntity;
import com.jlz.sale_pizza.persistence.entity.UserRoleEntity;
import com.jlz.sale_pizza.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {
  private final UserRepository userRepository;

  @Autowired
  public UserSecurityService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // Buscar el usuario o retornar una excepsión
    UserEntity userEntity = this.userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("User" + username + " not found"));

    // Obtener los roles de usuario
    String[] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);

    // Construir el usuario
    return User.builder()
        .username(userEntity.getUsername())
        .password(userEntity.getPassword())
        .authorities(this.grantedAuthorityList(roles))
        .accountLocked(userEntity.getLocked())
        .disabled(userEntity.getDisabled())
        .build();
  }

  // Agrega un role puntual
  private String[] getAuthorities(String role){
    if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
      // Agregar el nuevo role puntual si cumple la condición
      return new String[] {"random_order"};
    }
    return new String[] {};
  }

  // Crea roles
  private List<GrantedAuthority> grantedAuthorityList(String[] roles){
    // Crea una lista de GrantedAuthority de igual tamaño que la lista de roles recibida
    List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

    // Recorremos la lista de roles y para cada uno agregamos un nuevo objeto GrantedAuthority a la lista authorities
    for (String role: roles){
      // Debemos agregar el prefijo ROLE_ para que spring lo reconozca como un role
      authorities.add(new SimpleGrantedAuthority("ROLE_"+role));

      // Agregar role plano
      for(String authority: this.getAuthorities(role)){
        authorities.add(new SimpleGrantedAuthority(authority));
      }
    }
    return authorities;
  }
}
