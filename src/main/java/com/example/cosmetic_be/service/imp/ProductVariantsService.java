package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.dto.ProductVariantDTO;
import com.example.cosmetic_be.model.ProductVariants;
import com.example.cosmetic_be.model.Products;
import com.example.cosmetic_be.model.Subcategories;
import com.example.cosmetic_be.repository.IProductRepository;
import com.example.cosmetic_be.repository.IProductVariantsRepository;
import com.example.cosmetic_be.service.IProductVariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service

public class ProductVariantsService implements IProductVariantsService {
    @Autowired
    private IProductVariantsRepository iProductVariantsRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public Iterable<ProductVariants> findAll() {
        return iProductVariantsRepository.findAll();
    }

    @Override
    public Optional<ProductVariants> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductVariants save(ProductVariants productVariants) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
    public ProductVariants createVariant(ProductVariantDTO productVariantDTO,Long productId){
        Products products = iProductRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(" Sản phẩm k tìm thấy"));
        ProductVariants productVariants=new ProductVariants();
        productVariants.setName(productVariantDTO.getName());
        productVariants.setProducts(products);
        productVariants.setCreatedAt(LocalDateTime.now());
        iProductVariantsRepository.save(productVariants);
        return productVariants;
    }
    public ProductVariants updateVariant(Long id,ProductVariantDTO productVariantDTO){
        ProductVariants productVariants = iProductVariantsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Biến thể k tìm thấy"));
        productVariants.setName(productVariantDTO.getName());
        productVariants.setUpdatedAt(LocalDateTime.now());
        iProductVariantsRepository.save(productVariants);
        return productVariants;
    }
}
