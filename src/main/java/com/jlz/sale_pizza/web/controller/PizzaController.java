package com.jlz.sale_pizza.web.controller;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import com.jlz.sale_pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
  @Autowired
  private PizzaService pizzaService;

  @GetMapping
  public ResponseEntity<List<PizzaEntity>> getAll(){
    List<PizzaEntity> pizzas = pizzaService.getAll();
    if(pizzas == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizzas);
  }

  @GetMapping("/unavailable")
  public ResponseEntity<List<PizzaEntity>> getAllUnAvailable(){
    List<PizzaEntity> pizzas = pizzaService.getUnavailable();
    if(pizzas == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizzas);
  }

}
