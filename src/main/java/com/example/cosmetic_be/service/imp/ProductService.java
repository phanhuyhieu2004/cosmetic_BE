package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.Products;
import com.example.cosmetic_be.repository.IProductRepository;
import com.example.cosmetic_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;
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
}
