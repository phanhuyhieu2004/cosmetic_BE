package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.model.Subcategories;
import com.example.cosmetic_be.service.imp.SubcategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/subcategories")
public class SubcategoriesController {
    @Autowired
    private SubcategoriesService subcategoriesService;
    @GetMapping("")
    public ResponseEntity<Iterable<Subcategories>>getSubcategories(){
        Iterable<Subcategories> subcategories=subcategoriesService.findAll();
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<Iterable<Subcategories>>getSubcategoriesById(@PathVariable Long categoryId){
        Iterable<Subcategories> subcategories=subcategoriesService.finAllByCategory(categoryId);
        return new ResponseEntity<>(subcategories,HttpStatus.OK);

    }
}
