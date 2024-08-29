package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.dto.ProductDTO;
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
        Iterable<Products> products=iProductRepository.findAllByCreatedAt();
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
    @GetMapping("/product/search")
    public ResponseEntity<?>getProductsByName(@RequestParam String name){
        Iterable<Products> products=iProductRepository.findProductsByTitle(name);
        return new ResponseEntity<>(products,HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody ProductDTO productDTO) {
        Products createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO) {
        Products createdProduct = productService.updateProduct(id,productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Products> deleteProduct(
            @PathVariable Long id) {
        Optional<Products> createdProduct = iProductRepository.findById(id);
        if(!createdProduct.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductRepository.deleteById(createdProduct.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
