package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderItemRepository  extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    @Query(value = "SELECT SUM(oi.quantity)\n" +
            "FROM order_items oi\n" +
            "JOIN orders o ON oi.order_id = o.id\n" +
            "WHERE o.payment_status = 'Hoàn thành';",nativeQuery = true)
    Integer findTotalQuantitySold();
    @Query("SELECT oi.product.id, oi.product.name, SUM(oi.quantity) AS totalSold " +
            "FROM OrderItem oi " +
            "GROUP BY oi.product.id, oi.product.name " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTopSellingProducts();
}
