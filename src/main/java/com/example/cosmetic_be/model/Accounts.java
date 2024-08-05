package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pass;

    public Accounts() {
    }

    public Accounts(Long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }
}
