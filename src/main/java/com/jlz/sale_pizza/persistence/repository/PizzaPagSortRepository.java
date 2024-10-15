package com.jlz.sale_pizza.persistence.repository;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
  Page<PizzaEntity> findAllByAvailableTrue(Pageable pageable);

}
