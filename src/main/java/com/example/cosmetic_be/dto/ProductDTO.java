package com.example.cosmetic_be.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public  class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, BigDecimal price, String brand, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
    }
}