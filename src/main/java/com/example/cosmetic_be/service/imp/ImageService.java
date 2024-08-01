package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.Images;
import com.example.cosmetic_be.repository.IImageRepository;
import com.example.cosmetic_be.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;
@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository iImageRepository;

    @Override
    public Iterable<Images> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Images> findById(Long id) {
        return iImageRepository.findById(id);
    }

    @Override
    public Images save(Images images) {
        return iImageRepository.save(images);
    }

    @Override
    public void remove(Long id) {
        iImageRepository.deleteById(id);
    }
}
