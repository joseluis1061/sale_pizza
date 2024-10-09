package com.jlz.sale_pizza.web.controller;

import com.jlz.sale_pizza.persistence.entity.OrderEntity;

import com.jlz.sale_pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping
  public ResponseEntity<List<OrderEntity>> getAll(){
    List<OrderEntity> orders = orderService.getAll();
    if(orders == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(orders);
  }

  @GetMapping("/today")
  public ResponseEntity<List<OrderEntity>> getTodayOrders(){
    List<OrderEntity> orders = orderService.getTodayOrders();
    if(orders == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(orders);
  }

  @GetMapping("/outside")
  public ResponseEntity<List<OrderEntity>> getOutside(){
    List<OrderEntity> orders = orderService.getOrdersOutside();
    if(orders == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(orders);
  }

}
