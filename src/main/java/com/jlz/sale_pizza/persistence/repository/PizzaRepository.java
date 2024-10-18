package com.jlz.sale_pizza.persistence.repository;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

  List<PizzaEntity> findAllByAvailable(boolean available);

  List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

  PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

  List<PizzaEntity> findAllByDescriptionContainingIgnoreCase(String ingredient);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredient);

  int countByVegetarianTrueAndAvailableTrue();

  Optional<PizzaEntity> findFirstByNameIgnoreCase(String name);

  List<PizzaEntity> findTop3FindByPriceLessThanEqual(Double price);

  @Query(value = "UPDATE pizza" +
      "SET price = :newPrice" +
      "WHERE id_pizza = :pizzaId", nativeQuery = true)
  @Modifying
  void updatePrice(@Param("pizzaId") int pizzaId, @Param("newPrice") double newPrice);


}
