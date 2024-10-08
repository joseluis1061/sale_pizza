package com.jlz.sale_pizza.web.controller;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import com.jlz.sale_pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/available")
  public ResponseEntity<List<PizzaEntity>> getAllAvailable(){
    List<PizzaEntity> pizzas = pizzaService.getAvailable();
    if(pizzas == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizzas);
  }

  @GetMapping("/pizzaByName/{name}")
  public ResponseEntity<PizzaEntity> getPizzaByName(@PathVariable String name){
    PizzaEntity pizza = pizzaService.getPizzaByName(name);
    if(pizza == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizza);
  }

  @GetMapping("/withIngredient/{ingredient}")
  public ResponseEntity<List<PizzaEntity> > getPizzaWithIngredient(@PathVariable String ingredient){
    List<PizzaEntity>  pizzas = pizzaService.getPizzaByDescriptionWith(ingredient);
    if(pizzas == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizzas);
  }

  @GetMapping("/withOutIngredient/{ingredient}")
  public ResponseEntity<List<PizzaEntity>> getPizzaWithOutIngredient(@PathVariable String ingredient){
    List<PizzaEntity>  pizzas = pizzaService.getPizzaByDescriptionWithOut(ingredient);
    if(pizzas == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizzas);
  }

  @PostMapping("/save")
  public ResponseEntity<PizzaEntity> savePizza(@RequestBody PizzaEntity pizza){
    if(pizza.getIdPizza() == null || !this.pizzaService.existPizza(pizza.getIdPizza())){
      return ResponseEntity.ok(pizzaService.addPizza(pizza));
    }
    return ResponseEntity.badRequest().build();
  }

  @PutMapping("/update")
  public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizza){
    if(pizza.getIdPizza() != null || this.pizzaService.existPizza(pizza.getIdPizza())){
      return ResponseEntity.ok(pizzaService.addPizza(pizza));
    }
    return ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/delete/{idPizza}")
  public ResponseEntity<Void> deletePizza(@PathVariable Integer idPizza){
    if(this.pizzaService.existPizza(idPizza)){
      this.pizzaService.deletePizza(idPizza);
      return ResponseEntity.ok().build();
    }else{
      return ResponseEntity.badRequest().build();
    }
  }

}
