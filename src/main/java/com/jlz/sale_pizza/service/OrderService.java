package com.jlz.sale_pizza.service;

import com.jlz.sale_pizza.persistence.entity.OrderEntity;
import com.jlz.sale_pizza.persistence.projection.OrderSumary;
import com.jlz.sale_pizza.persistence.repository.OrderRepository;
import com.jlz.sale_pizza.service.dto.RandomOrderDto;
import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  private static final char DELIVERY = 'D';
  private static final char CARRYOUT = 'C';
  private static final char ON_SITE = 'S';

  public List<OrderEntity> getAll(){
    return this.orderRepository.findAll();
  }

  public List<OrderEntity> getTodayOrders(){
    LocalDateTime today = LocalDate.now().atTime(0,0);
    return this.orderRepository.findAllByDateAfter(today);
  }

  public List<OrderEntity> getOrdersOutside(){
    List<Character> methods = Arrays.asList(DELIVERY, CARRYOUT);
    return this.orderRepository.findAllByMethodIn(methods);
  }

  public List<OrderEntity> getByCustomerid(String idCustomer){
    return this.orderRepository.findByCustomerId(idCustomer);
  }

  public OrderSumary getOrderSumary(int orderId){
    return this.orderRepository.finSumary(orderId);
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

  @Transactional
  public boolean saveRandomOrder(RandomOrderDto randomOrderDto){
    return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
  }

}
