package com.jlz.sale_pizza.service;

import com.jlz.sale_pizza.persistence.entity.OrderEntity;
import com.jlz.sale_pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<OrderEntity> getAll(){
    return this.orderRepository.findAll();
  }

  public Optional<OrderEntity> getById(Integer idOrder){
    return this.orderRepository.findById(idOrder);
  }

  public OrderEntity saveOrder(OrderEntity order){
    return this.orderRepository.save(order);
  }

  public Boolean existOrder(Integer idOrder){
    return this.orderRepository.existsById(idOrder);
  }

  public void deleteOrderBiId(int idOrder){
    this.orderRepository.deleteById(idOrder);
  }
}
