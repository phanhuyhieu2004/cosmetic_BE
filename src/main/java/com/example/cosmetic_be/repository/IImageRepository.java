package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface IImageRepository extends JpaRepository<Images,Long> {
    @Query(value = "SELECT * FROM images WHERE product_id=?",nativeQuery = true)
  Iterable<Images> getImagesByProductId(@PathVariable Long productId);
}
