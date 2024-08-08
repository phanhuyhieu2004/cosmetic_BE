package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Cart;
import com.example.cosmetic_be.model.CartItems;
import com.example.cosmetic_be.model.ProductVariants;
import com.example.cosmetic_be.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICartItemRepository extends JpaRepository<CartItems, Long> {
    CartItems findByCartAndProductAndVariant(Cart cart, Products product, ProductVariants variant);
    List<CartItems> findByCartId(Long cartId);
}
