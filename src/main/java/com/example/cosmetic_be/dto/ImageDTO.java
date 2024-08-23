package com.example.cosmetic_be.dto;

import com.example.cosmetic_be.model.Products;
import lombok.Data;

@Data
public class ImageDTO {
    private String name;
    public ImageDTO() {
    }

    public ImageDTO(String name) {
        this.name = name;

    }
}
