package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Subcategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubcategoriesRepository extends JpaRepository<Subcategories,Long> {
    @Query(value = "SELECT * FROM subcategories WHERE category_id=?",nativeQuery = true)
    List<Subcategories> findSubcategoriesByCategories(Long categoryId );
}
