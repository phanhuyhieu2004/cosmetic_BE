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
//Mỗi mục hàng thuoc về 1 đơn hàng ,1 đơn hàng chứa nhiều mục hàng
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
//1 mục hàng chỉ có 1 sp nhưng 1 sp xuất hiện ở nhiều mục hàng
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;
//1 mục hàng có 1 biến thể nhưng 1 biến thể xuất hiện trong nhều mục hàng
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