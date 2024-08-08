package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByAccountId(Long accountId);
}
