package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subcategories")
public class Subcategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;



    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private  Categories categories;

    public Subcategories() {
    }

    public Subcategories(Long id, String name, Categories categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
    }
}