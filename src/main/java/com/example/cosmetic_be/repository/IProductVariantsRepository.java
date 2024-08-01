package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductVariantsRepository extends JpaRepository<ProductVariants,Long> {
}
