package com.jlz.sale_pizza.persistence.projection;

import java.time.LocalDate;

public interface OrderSumary {
  Integer getIdOrder();
  String  getCustomerName();
  LocalDate getOrderDate();
  Double getOrderTotal();
  String getPizzaNames();
}
