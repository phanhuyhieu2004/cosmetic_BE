package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cartItems")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariants variant;
    private int quantity;

    public CartItems() {
    }

    public CartItems(Long id, Cart cart, Products product, ProductVariants variant, int quantity) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.variant = variant;
        this.quantity = quantity;
    }
}
