package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//1 danh mục con thì có nhiều sp nhưng 1 sp thì chỉ có 1 danh mục con,cha
    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private  Subcategories subcategories;

    private String name;

    @Column(columnDefinition = "longtext")
    private String description;

    private BigDecimal price;
    private String brand;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
//    là một annotation trong Spring Data JPA được sử dụng để tự động ghi lại thời gian mà một entity (bản ghi) được cập nhật lần cuối trong cơ sở dữ liệu.
    @LastModifiedDate
    private LocalDateTime updatedAt;
private int quantity;

    public Products() {

    }

    public Products(Long id, Subcategories subcategories, String name, String description, BigDecimal price, String brand, LocalDateTime createdAt, LocalDateTime updatedAt, int quantity) {
        this.id = id;
        this.subcategories = subcategories;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.quantity = quantity;
    }
}