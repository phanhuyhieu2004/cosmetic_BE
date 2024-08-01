package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.model.Categories;
import com.example.cosmetic_be.model.Images;
import com.example.cosmetic_be.repository.IImageRepository;
import com.example.cosmetic_be.service.imp.CategoriesService;
import com.example.cosmetic_be.service.imp.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
@CrossOrigin("*")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private IImageRepository iImageRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Images>> getAllImages() {
        Iterable<Images> images = imageService.findAll();

        return new ResponseEntity<>(images, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Images>>getImagesById(@PathVariable Long id){
        Iterable<Images> images=iImageRepository.getImagesByProductId(id);
        return new ResponseEntity<>(images,HttpStatus.OK);
    }
}
