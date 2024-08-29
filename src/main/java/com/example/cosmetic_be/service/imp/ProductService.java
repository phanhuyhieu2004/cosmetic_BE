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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Products updateProduct(Long id, ProductDTO productDTO) {
        Products updatedProduct = iProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm được sản phẩm"));

        updatedProduct.setName(productDTO.getName());
        updatedProduct.setBrand(productDTO.getBrand());
        updatedProduct.setUpdatedAt(LocalDateTime.now());
        updatedProduct.setDescription(productDTO.getDescription());
        updatedProduct.setPrice(productDTO.getPrice());
        updatedProduct.setQuantity(productDTO.getQuantity());
        Subcategories subcategory = iSubcategoriesRepository.findById(productDTO.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Danh mục con không tìm thấy"));
        updatedProduct.setSubcategories(subcategory);
        iProductRepository.save(updatedProduct);
//tìm ảnh dựa vào id cuủa sp
        List<Images> existingImages = (List<Images>) iImageRepository.getImagesByProductId(id);
        Map<Long, Images> existingImageMap = new HashMap<>();
        for (Images img : existingImages) {
            existingImageMap.put(img.getId(), img);
        }


        for (ImageDTO imageDTO : productDTO.getImages()) {
            Long imageId = imageDTO.getId();
            String newName = imageDTO.getName();

            if (imageId != null) {
                Images existingImage = existingImageMap.get(imageId);
                if (existingImage != null) {
                    existingImage.setName(newName);
                    iImageRepository.save(existingImage);
                } else {
                    System.out.println("Không tìm thấy ảnh với ID: " + imageId);
                }
            } else {
                System.out.println("Không có id");
            }
        }

        return updatedProduct;
    }
}