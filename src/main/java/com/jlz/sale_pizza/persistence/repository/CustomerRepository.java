package com.jlz.sale_pizza.persistence.repository;

import com.jlz.sale_pizza.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Integer> {

  @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber= :phone ")
  CustomerEntity findByPhone(@Param("phone") String phone);
}
