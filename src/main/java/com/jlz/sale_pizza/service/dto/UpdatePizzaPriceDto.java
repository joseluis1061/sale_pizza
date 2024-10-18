package com.jlz.sale_pizza.service.dto;


import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
  private int pizzaId;
  private double newPrice;
}
