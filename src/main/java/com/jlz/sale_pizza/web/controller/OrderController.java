package com.jlz.sale_pizza.web.controller;

import com.jlz.sale_pizza.persistence.entity.OrderEntity;

import com.jlz.sale_pizza.persistence.projection.OrderSumary;
import com.jlz.sale_pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/byCustomerId/{idCustomer}")
  public ResponseEntity<List<OrderEntity>> getByCustomerId(@PathVariable String idCustomer){
    List<OrderEntity> orders = orderService.getByCustomerid(idCustomer);
    if(orders == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(orders);
  }

  @GetMapping("/summary/{idOrder}")
  public ResponseEntity<OrderSumary> getSummary(@PathVariable int idOrder){
    OrderSumary orderSumary = orderService.getOrderSumary(idOrder);
    if(orderSumary == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(orderSumary);
  }

}
