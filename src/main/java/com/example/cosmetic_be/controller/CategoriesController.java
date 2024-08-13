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


}