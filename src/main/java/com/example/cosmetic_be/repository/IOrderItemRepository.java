package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderItemRepository  extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    @Query(value = "SELECT SUM(oi.quantity) FROM order_items  oi",nativeQuery = true)
    Integer findTotalQuantitySold();
}
