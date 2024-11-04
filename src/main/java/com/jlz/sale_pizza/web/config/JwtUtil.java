package com.jlz.sale_pizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

  private static String SECRET_KEY = "p1zz4_c0ntro113r";
  private static Algorithm ALGORITM = Algorithm.HMAC256(SECRET_KEY);

  public String create(String username){
    return JWT.create()
        .withSubject(username)
        .withIssuer("pizza_control")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
        .sign(ALGORITM);
  }

  public boolean isValid(String jwt){
    try {
      JWT.require(ALGORITM)
          .build()
          .verify(jwt); // Decodifica el JWT
      return true;

    }catch (JWTVerificationException e){
      return false;
    }
  }

  public String getUserName(String jwt) {
    return JWT.require(ALGORITM)
        .build()
        .verify(jwt)
        .getSubject(); // Recuperamos el subject del JWT
  }
}
