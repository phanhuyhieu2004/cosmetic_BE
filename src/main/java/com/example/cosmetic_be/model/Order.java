package com.example.cosmetic_be.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//1 đơn chỉ có 1 tài khoản nhưng 1 tài khoản có nhiều đơn
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Accounts account;

    private BigDecimal totalPrice;

    private String paymentStatus;

    private String shippingStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;



    public Order() {
    }

    public Order(Long id, Accounts account, BigDecimal totalPrice, String paymentStatus, String shippingStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.account = account;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
        this.shippingStatus = shippingStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
