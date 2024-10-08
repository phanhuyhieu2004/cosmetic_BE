package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Products;
import com.example.cosmetic_be.model.Subcategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Products,Long> {
    @Query(value = "SELECT * FROM products WHERE subcategory_id=?",nativeQuery = true)
    List<Products> findProductsBySubcategories(Long subcategoriesId );
    @Query(value = "SELECT * FROM products WHERE name LIKE %:name%",nativeQuery = true)
    public Iterable<Products> findProductsByTitle(@RequestParam String name);
    @Query(value = "SELECT * FROM products ORDER BY created_at DESC",nativeQuery = true)
    public Iterable<Products> findAllByCreatedAt();
}
