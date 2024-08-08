package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariants variant;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(Long id, Order order, Products product, ProductVariants variant, Integer quantity, BigDecimal price) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.variant = variant;
        this.quantity = quantity;
        this.price = price;
    }
}