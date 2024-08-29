package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.dto.ProductDTO;
import com.example.cosmetic_be.dto.ProductVariantDTO;
import com.example.cosmetic_be.model.ProductVariants;
import com.example.cosmetic_be.repository.IProductVariantsRepository;
import com.example.cosmetic_be.service.imp.ProductVariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/variants")
public class ProductVariantsController{
    @Autowired
    private ProductVariantsService productVariantsService;
    @Autowired
    private IProductVariantsRepository iProductVariantsRepository;
    @GetMapping("")
    public ResponseEntity<Iterable<ProductVariants>> getProductVariants(){
        Iterable<ProductVariants>productVariants=productVariantsService.findAll();
        return new ResponseEntity<>(productVariants, HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Iterable<ProductVariants>> getProductVariantsByProductId(@PathVariable Long productId){
        Iterable<ProductVariants>productVariants=iProductVariantsRepository.findProductVariantsByProducts_Id(productId);
        return new ResponseEntity<>(productVariants, HttpStatus.OK);
    }
  @PostMapping("/{productId}")
    public ResponseEntity<ProductVariants> createVariant(@RequestBody ProductVariantDTO productVariantDTO,@PathVariable Long productId){
        ProductVariants productVariants=productVariantsService.createVariant(productVariantDTO,productId);
        return new ResponseEntity<>( productVariants,HttpStatus.OK);
    }
    @GetMapping("/variant/{id}")
    public ResponseEntity<ProductVariants> detailVariant(@PathVariable Long id){
        Optional<ProductVariants> variant=iProductVariantsRepository.findById(id);
        return new ResponseEntity<>( variant.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductVariants> updateVariant(@PathVariable Long id,@RequestBody ProductVariantDTO productVariantDTO){
        ProductVariants productVariants=productVariantsService.updateVariant(id,productVariantDTO);
        return new ResponseEntity<>( productVariants,HttpStatus.OK);
    }
}
