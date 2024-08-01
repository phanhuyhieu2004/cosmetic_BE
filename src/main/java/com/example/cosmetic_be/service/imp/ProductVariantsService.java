package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.ProductVariants;
import com.example.cosmetic_be.repository.IProductVariantsRepository;
import com.example.cosmetic_be.service.IProductVariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class ProductVariantsService implements IProductVariantsService {
    @Autowired
    private IProductVariantsRepository iProductVariantsRepository;
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
}
