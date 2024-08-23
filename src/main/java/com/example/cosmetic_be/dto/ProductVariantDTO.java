package com.example.cosmetic_be.dto;

import com.example.cosmetic_be.model.Products;
import lombok.Data;

@Data
public class ProductVariantDTO {
    private String name;
    private Products products;
}
