package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.CartItems;
import com.example.cosmetic_be.repository.ICartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemsService {
    @Autowired
    private ICartItemRepository iCartItemRepository;
    public Optional<CartItems> deleteCartItem(Long itemId) {
        Optional<CartItems> item = iCartItemRepository.findById(itemId);
        if (item.isPresent()) {
            iCartItemRepository.deleteById(itemId);
        }
        return item;
    }
}
