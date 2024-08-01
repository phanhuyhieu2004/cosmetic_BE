package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.Categories;
import com.example.cosmetic_be.repository.ICategoriesRepository;
import com.example.cosmetic_be.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoriesService implements ICategoriesService {
    @Autowired
    private ICategoriesRepository iCategoriesRepository;
    @Override
    public Iterable<Categories> findAll() {
        return iCategoriesRepository.findAll();
    }

    @Override
    public Optional<Categories> findById(Long id) {
        return iCategoriesRepository.findById(id);
    }

    @Override
    public Categories save(Categories categories) {
        return iCategoriesRepository.save(categories);
    }

    @Override
    public void remove(Long id) {
iCategoriesRepository.deleteById(id);
    }

}
