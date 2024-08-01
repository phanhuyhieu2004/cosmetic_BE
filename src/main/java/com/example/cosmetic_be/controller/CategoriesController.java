package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.model.Categories;
import com.example.cosmetic_be.service.imp.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("")
    public ResponseEntity<Iterable<Categories>> getAllCategories() {
        Iterable<Categories> categories = categoriesService.findAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        Optional<Categories> categories = categoriesService.findById(id);
        return categories.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("")
    public ResponseEntity<Categories> createCategory(@RequestBody Categories category) {
        Categories createdCategory = categoriesService.save(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable Long id, @RequestBody Categories categories) {
        Optional<Categories> existingCategory = categoriesService.findById(id);
        if (existingCategory.isPresent()) {
            categories.setId(id);
            Categories updatedCategory = categoriesService.save(categories);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Optional<Categories> existingCategory = categoriesService.findById(id);
        if (existingCategory.isPresent()) {
            categoriesService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}