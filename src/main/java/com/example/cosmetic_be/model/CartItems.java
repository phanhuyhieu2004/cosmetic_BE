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
//1 mục giỏ hàng chỉ có 1 giỏ hàng nhưng 1 giỏ hàng có nhieu mục giỏ hàng
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
//1 mục giỏ hàng chỉ có 1 sản phẩm nhưng 1 sản phẩm có nhiều mục giỏ hàng
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
//1 mục giỏ hàng có 1 biến thể nhưng 1 biến thể có ở nhiều mục giỏ hàng khác nhau
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
