package com.jlz.sale_pizza.persistence.repository;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

  List<PizzaEntity> findAllByAvailable(boolean available);
}
