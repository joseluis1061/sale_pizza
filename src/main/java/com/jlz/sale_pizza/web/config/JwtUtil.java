package com.jlz.sale_pizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
}
