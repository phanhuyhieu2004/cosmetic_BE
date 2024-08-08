package com.example.cosmetic_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Accounts accounts;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Cart() {
    }

    public Cart(Long id, Accounts accounts, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.accounts = accounts;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
