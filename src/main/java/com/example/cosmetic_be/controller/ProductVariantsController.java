package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.model.ProductVariants;
import com.example.cosmetic_be.service.imp.ProductVariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/variants")
public class ProductVariantsController{
    @Autowired
    private ProductVariantsService productVariantsService;
    @GetMapping("")
    public ResponseEntity<Iterable<ProductVariants>> getProductVariants(){
        Iterable<ProductVariants>productVariants=productVariantsService.findAll();
        return new ResponseEntity<>(productVariants, HttpStatus.OK);
    }
}
