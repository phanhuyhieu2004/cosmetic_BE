package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
