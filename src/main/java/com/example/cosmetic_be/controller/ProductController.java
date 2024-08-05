package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.model.Products;
import com.example.cosmetic_be.model.Subcategories;
import com.example.cosmetic_be.repository.IProductRepository;
import com.example.cosmetic_be.service.imp.ProductService;
import com.example.cosmetic_be.service.imp.SubcategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private IProductRepository iProductRepository;
    @GetMapping("")
    public ResponseEntity<Iterable<Products>> getProducts(){
        Iterable<Products> products=productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{subcategoriesId}")
    public ResponseEntity<Iterable<Products>>getProductsBySubcategoriesId(@PathVariable Long subcategoriesId){
        Iterable<Products> products=iProductRepository.findProductsBySubcategories(subcategoriesId);
        return new ResponseEntity<>(products,HttpStatus.OK);

    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Products>getProductsById(@PathVariable Long id){
        Optional<Products> products=iProductRepository.findById(id);
        return new ResponseEntity<>(products.get(),HttpStatus.OK);

    }
}
