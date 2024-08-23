package com.example.cosmetic_be.service;

import com.example.cosmetic_be.model.Products;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface IProductService extends IGeneralService<Products>{

}
