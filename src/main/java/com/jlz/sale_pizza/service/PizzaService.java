package com.jlz.sale_pizza.service;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import com.jlz.sale_pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
  private final PizzaRepository pizzaRepository;

  @Autowired
  public PizzaService(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  public List<PizzaEntity> getAll(){
    return this.pizzaRepository.findAll();

  }

  public List<PizzaEntity> getUnavailable(){
    return this.pizzaRepository.findAllByAvailable(false);
  }

  public List<PizzaEntity> getAvailable(){
    return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
  }

  public PizzaEntity getPizzaByName(String name){
    return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
  }

  public List<PizzaEntity>  getPizzaByDescriptionWith(String ingredient){
    return this.pizzaRepository.findAllByDescriptionContainingIgnoreCase(ingredient);
  }

  public List<PizzaEntity>  getPizzaByDescriptionWithOut(String ingredient){
    return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
  }

  public int getCountPizzaVegetar(){
    return this.pizzaRepository.countByVegetarianTrueAndAvailableTrue();
  }


  public PizzaEntity addPizza(PizzaEntity pizza){
    return this.pizzaRepository.save(pizza);
  }

  public Boolean existPizza(Integer idPizza){
    return this.pizzaRepository.existsById(idPizza);
  }

  public void deletePizza(Integer idPizza){
    this.pizzaRepository.deleteById(idPizza);
  }
}
