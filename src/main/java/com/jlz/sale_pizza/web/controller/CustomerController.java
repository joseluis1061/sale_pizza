package com.jlz.sale_pizza.web.controller;

import com.jlz.sale_pizza.persistence.entity.CustomerEntity;
import com.jlz.sale_pizza.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

  @Autowired
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<CustomerEntity>> getALl(){
    List<CustomerEntity> customers = this.customerService.findAll();
    if(customers == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(customers);
  }

  @GetMapping("/byphone/{phone}")
  public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone){
    CustomerEntity customer = this.customerService.findByPhone(phone);
    if(customer == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(customer);
  }


}
