package com.example.cosmetic_be.dto;

import com.example.cosmetic_be.model.Products;
import lombok.Data;

@Data
public class ImageDTO {
    private Long id; // Thêm trường ID
    private String name;
    public ImageDTO() {
    }

    public ImageDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
