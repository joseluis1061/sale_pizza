package com.jlz.sale_pizza.service;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import com.jlz.sale_pizza.persistence.repository.PizzaPagSortRepository;
import com.jlz.sale_pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
  private final PizzaRepository pizzaRepository;
  private final PizzaPagSortRepository pizzaPagSortRepository;

  @Autowired
  public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
    this.pizzaRepository = pizzaRepository;
    this.pizzaPagSortRepository = pizzaPagSortRepository;
  }

  public Page<PizzaEntity> getAll(int page, int elements){
    Pageable pageRequest = PageRequest.of(page, elements);
    return this.pizzaPagSortRepository.findAll(pageRequest);
  }

  public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy){
    Pageable pageRequest = PageRequest.of(page, elements, Sort.by(sortBy));
    return this.pizzaPagSortRepository.findAllByAvailableTrue(pageRequest);
  }

  public List<PizzaEntity> getUnavailable(){
    return this.pizzaRepository.findAllByAvailable(false);
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

  public PizzaEntity getFirstByName(String name){
    return this.pizzaRepository.findFirstByNameIgnoreCase(name).orElseThrow(()-> new RuntimeException("No existe pizza con este nombre"));
  }

  public List<PizzaEntity> getCheapest(Double price) {
    return this.pizzaRepository.findTop3FindByPriceLessThanEqual(price);
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
