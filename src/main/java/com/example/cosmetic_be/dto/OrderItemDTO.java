package com.example.cosmetic_be.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private VariantDTO variant;
    private int quantity;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long id, ProductDTO product, VariantDTO variant, int quantity) {
        this.id = id;
        this.product = product;
        this.variant = variant;
        this.quantity = quantity;
    }
}
