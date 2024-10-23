package com.jlz.sale_pizza.persistence.audit;

import com.jlz.sale_pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

public class AuditPizzaListener {
  private PizzaEntity currentValue;

  @PostLoad
  public void postLoad(PizzaEntity entity) {
    System.out.println("POST LOAD...");
    this.currentValue = SerializationUtils.clone(entity);
  }

  @PostPersist
  @PostUpdate
  public void onPostPersist(PizzaEntity entity) {
    System.out.println("POST PERSIST OR UPDATE");
    System.out.println("OLD VALUE: " + currentValue);
    System.out.println("OLD VALUE: " + entity.toString());
  }

  @PreRemove
  public void onPreDelete(PizzaEntity entity) {
    System.out.println(entity.toString());
  }
}
