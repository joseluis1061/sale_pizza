package com.jlz.sale_pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

  @Bean
  UrlBasedCorsConfigurationSource corsConfigurationSource() {
    // Objeto para configuraciones globales
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    // Lista de urls externas permitidas
    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
    // Lista de metodos permitidos
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE"));
    // Lista de headers pemitidos con * son todos
    corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

    // Agrega un objeto que permite agregar la configuración a los endPoints
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    // Agrega los endPoints que van a tener esta configuración
    source.registerCorsConfiguration("/**", corsConfiguration);

    return source;
  }
}
