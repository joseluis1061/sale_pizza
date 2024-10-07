package com.jlz.sale_pizza.persistence.repository;

import com.jlz.sale_pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
}
