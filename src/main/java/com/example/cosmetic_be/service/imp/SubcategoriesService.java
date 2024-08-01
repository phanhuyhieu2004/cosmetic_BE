package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.Categories;
import com.example.cosmetic_be.model.Subcategories;
import com.example.cosmetic_be.repository.ISubcategoriesRepository;
import com.example.cosmetic_be.service.ICategoriesService;
import com.example.cosmetic_be.service.ISubcategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SubcategoriesService implements ISubcategoriesService {
    @Autowired
    private ISubcategoriesRepository iSubcategoriesRepository;

    @Override
    public Iterable<Subcategories> findAll() {
        return iSubcategoriesRepository.findAll();
    }

    @Override
    public Optional<Subcategories> findById(Long id) {
        return iSubcategoriesRepository.findById(id);
    }

    @Override
    public Subcategories save(Subcategories subcategories) {
        return iSubcategoriesRepository.save(subcategories);
    }

    @Override
    public void remove(Long id) {
iSubcategoriesRepository.deleteById(id);
    }
    public Iterable<Subcategories> finAllByCategory(Long categoryId){
        return iSubcategoriesRepository.findSubcategoriesByCategories(categoryId);
    }
}
