package com.jlz.sale_pizza.service;

import com.jlz.sale_pizza.persistence.entity.CustomerEntity;
import com.jlz.sale_pizza.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<CustomerEntity> findAll(){
    return this.customerRepository.findAll();
  }

  public CustomerEntity findByPhone(String phone){
    return this.customerRepository.findByPhone(phone);
  }


}
