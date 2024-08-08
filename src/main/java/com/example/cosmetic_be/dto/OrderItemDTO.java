package com.example.cosmetic_be.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long productId;
    private Long variantId;
    private int quantity;
    private BigDecimal price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long productId, Long variantId, int quantity, BigDecimal price) {
        this.productId = productId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.price = price;
    }
}
