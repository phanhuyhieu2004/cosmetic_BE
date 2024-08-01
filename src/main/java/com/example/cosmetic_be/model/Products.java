package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private  Subcategories subcategories;

    private String name;

    @Column(columnDefinition = "longtext")
    private String description;

    private int price;
    private String brand;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;


    public Products() {

    }

    public Products(Long id, Subcategories subcategories, String name, String description, int price, String brand, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.subcategories = subcategories;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}