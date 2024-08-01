package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_variants")
public class ProductVariants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Products products;

    public ProductVariants() {
    }

    public ProductVariants(Long id, String name, Products products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }
}
