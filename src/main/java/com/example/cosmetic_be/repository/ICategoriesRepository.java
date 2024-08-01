package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriesRepository extends JpaRepository<Categories,Long> {

}
