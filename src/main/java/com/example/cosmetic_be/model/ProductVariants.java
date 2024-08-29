package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product_variants")
public class ProductVariants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    1 sp có nhiều biến thể còn 1 biến thể thì chỉ có ở 1 sp
    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Products products;
    @Column(name="created_at",nullable = false,updatable = false)
private LocalDateTime createdAt;
    @Column(name="updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
    public ProductVariants() {
    }

    public ProductVariants(Long id, String name, Products products, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
