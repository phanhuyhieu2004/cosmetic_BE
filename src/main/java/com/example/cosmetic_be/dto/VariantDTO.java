package com.example.cosmetic_be.dto;

import lombok.Data;

@Data
public  class VariantDTO {
    private Long id;
    private String name;
    private ProductDTO product;

    public VariantDTO() {
    }

    public VariantDTO(Long id, String name, ProductDTO product) {
        this.id = id;
        this.name = name;
        this.product = product;
    }
}