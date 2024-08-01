package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Categories() {
    }

    public Categories(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}