package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface IProductVariantsRepository extends JpaRepository<ProductVariants,Long> {
    @Query(value = "SELECT * FROM product_variants WHERE product_id=?",nativeQuery = true)
    public Iterable<ProductVariants>findProductVariantsByProducts_Id( Long productId);

}
