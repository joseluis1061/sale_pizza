package com.jlz.sale_pizza.persistence.repository;

import com.jlz.sale_pizza.persistence.entity.OrderEntity;
import com.jlz.sale_pizza.persistence.projection.OrderSumary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
  List<OrderEntity> findAllByDateAfter(LocalDateTime date);

  List<OrderEntity> findAllByMethodIn(List<Character> methods);

  @Query(value = "SELECT * FROM pizza_order o WHERE id_customer = :id", nativeQuery = true)
  List<OrderEntity> findByCustomerId(@Param("id") String idCustomer);

  @Query(value = "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, " +
      "           po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames " +
      "FROM pizza_order po " +
      "   INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
      "   INNER JOIN order_item oi ON po.id_order = oi.id_order " +
      "   INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza " +
      "WHERE po.id_order = :orderId " +
      "GROUP BY po.id_order, cu.name, po.date, po.total", nativeQuery = true)
  OrderSumary finSumary(@Param("orderId") int orderId);

  //  llamada al store procedure de pizza random con descuento
  @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
  boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);
}
