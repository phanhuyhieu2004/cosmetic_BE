package com.example.cosmetic_be.dto;

import com.example.cosmetic_be.model.Products;
import lombok.Data;

@Data
public class ProductVariantDTO {
    private String name;

    public ProductVariantDTO() {
    }

    public ProductVariantDTO(String name) {
        this.name = name;
    }
}
