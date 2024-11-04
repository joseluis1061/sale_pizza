package com.jlz.sale_pizza.web.controller;

import com.jlz.sale_pizza.service.dto.LoginDto;
import com.jlz.sale_pizza.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/login")
  public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
    // Crear un token de atutenticación
    UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
    // Enviar el usuario para corroborar si pasa  los filtros
    Authentication authentication = this.authenticationManager.authenticate(login);
    // Si un filtro no pasa se detiene el proceso y retorna un error

    // Si pasa podemos ver el código en este caso imprimimos los valores
    System.out.println("Usaurio autenticado: "+authentication.isAuthenticated());
    System.out.println(authentication.getPrincipal());

    // Creamos el jwt con los datos del usuario
    String jwt = this.jwtUtil.create(loginDto.getUsername());
    // Retornamos los datos con el header de autorización jwt
    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
  }
}
