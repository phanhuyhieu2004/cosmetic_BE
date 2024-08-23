package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.dto.ImageDTO;
import com.example.cosmetic_be.dto.ProductDTO;
import com.example.cosmetic_be.dto.ProductVariantDTO;
import com.example.cosmetic_be.model.Images;
import com.example.cosmetic_be.model.ProductVariants;
import com.example.cosmetic_be.model.Products;
import com.example.cosmetic_be.model.Subcategories;
import com.example.cosmetic_be.repository.IImageRepository;
import com.example.cosmetic_be.repository.IProductRepository;
import com.example.cosmetic_be.repository.IProductVariantsRepository;
import com.example.cosmetic_be.repository.ISubcategoriesRepository;
import com.example.cosmetic_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private ISubcategoriesRepository iSubcategoriesRepository;

    @Autowired
    private IProductVariantsRepository iProductVariantsRepository;

    @Autowired
    private IImageRepository iImageRepository;
    @Override
    public Iterable<Products> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Products> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public Products save(Products products) {
        return iProductRepository.save(products);
    }

    @Override
    public void remove(Long id) {
iProductRepository.deleteById(id);
    }

    public Products createProduct(ProductDTO productDTO) {
        Subcategories subcategory = iSubcategoriesRepository.findById(productDTO.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Danh mục con k tìm thấy"));

        Products product = new Products();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setBrand(productDTO.getBrand());
        product.setQuantity(productDTO.getQuantity());
        product.setSubcategories(subcategory);
        product.setCreatedAt(LocalDateTime.now());



        Products savedProduct = iProductRepository.save(product);



        for (ImageDTO imageDTO : productDTO.getImages()) {
            Images image = new Images();
            image.setName(imageDTO.getName());
            image.setProducts(savedProduct);
            iImageRepository.save(image);
        }

        return savedProduct;
    }
}