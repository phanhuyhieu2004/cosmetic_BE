package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ICartRepository  extends JpaRepository<Cart, Long> {
    Cart findByAccountsId(Long accountId);

}
