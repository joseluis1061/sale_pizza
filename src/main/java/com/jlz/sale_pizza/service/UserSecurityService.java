package com.jlz.sale_pizza.service;

import com.jlz.sale_pizza.persistence.entity.UserEntity;
import com.jlz.sale_pizza.persistence.entity.UserRoleEntity;
import com.jlz.sale_pizza.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        .roles(roles)
        .accountLocked(userEntity.getLocked())
        .disabled(userEntity.getDisabled())
        .build();
  }
}
