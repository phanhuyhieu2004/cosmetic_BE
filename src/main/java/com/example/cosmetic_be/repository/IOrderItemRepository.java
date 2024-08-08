package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository  extends JpaRepository<OrderItem, Long> {
}
