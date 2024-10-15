package com.jlz.sale_pizza.web.controller;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import com.jlz.sale_pizza.persistence.repository.PizzaPagSortRepository;
import com.jlz.sale_pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
  @Autowired
  private PizzaService pizzaService;


  @GetMapping
  public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0", required=true) int page,
                                                  @RequestParam(defaultValue = "8") int elements){
    Page<PizzaEntity> pizzas = pizzaService.getAll(page, elements);
    if(pizzas == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizzas);
  }

  @GetMapping("/available")
  public ResponseEntity<Page<PizzaEntity>> getAllAvailable(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "8") int elements,
                                                           @RequestParam(defaultValue = "price") String sortBy,
                                                           @RequestParam(defaultValue = "ASC") String sortDirection){
    Page<PizzaEntity> pizzas = pizzaService.getAvailable(page, elements, sortBy, sortDirection);
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



  @GetMapping("/first-by-name/{name}")
  public ResponseEntity<PizzaEntity> getFirstByName(@PathVariable String name){
    PizzaEntity pizza = pizzaService.getFirstByName(name);
    if(pizza == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizza);
  }

  @GetMapping("/getCheapest/{price}")
  public ResponseEntity<List<PizzaEntity>> getPizzaCheapest(@PathVariable Double price){
    List<PizzaEntity> pizza = pizzaService.getCheapest(price);
    if(pizza == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pizza);
  }

  @GetMapping("/count-vegetarian")
  public ResponseEntity<Integer> countVegetarian(){
    int count = pizzaService.getCountPizzaVegetar();
    return ResponseEntity.ok(count);
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
