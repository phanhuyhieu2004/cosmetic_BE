package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.model.Cart;
import com.example.cosmetic_be.model.CartItems;
import com.example.cosmetic_be.repository.ICartItemRepository;
import com.example.cosmetic_be.repository.ICartRepository;
import com.example.cosmetic_be.service.imp.CartItemsService;
import com.example.cosmetic_be.service.imp.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")

public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ICartRepository iCartRepository;

    @Autowired
    private CartItemsService cartItemService;
    @Autowired
    private ICartItemRepository iCartItemRepository;
    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(
            @RequestParam Long accountId,
            @RequestParam Long productId,
            @RequestParam(required = false) Long variantId, // Variant ID is optional
            @RequestParam int quantity) {
        try {
            cartService.addProductToCart(accountId, productId, variantId, quantity);
            return ResponseEntity.ok("Sản phẩm đã được thêm vào giỏ hàng");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @GetMapping("/cartId")
    public ResponseEntity<Long> getCartId(@RequestParam Long accountId) {
        Cart cart = iCartRepository.findByAccountsId(accountId);
        if (cart != null) {
            return ResponseEntity.ok(cart.getId());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cartItems")
    public ResponseEntity<List<CartItems>> getCartItems(@RequestParam Long cartId) {
        List<CartItems> cartItems = iCartItemRepository.findByCartId(cartId);
        return ResponseEntity.ok(cartItems);
    }


    @PostMapping("/updateQuantity")
    public ResponseEntity<String> updateQuantity(
            @RequestParam Long itemId,
            @RequestParam int newQuantity) {
        try {
            cartService.updateQuantity(itemId, newQuantity);
            return ResponseEntity.ok("Số lượng đã được cập nhật");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }
    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<CartItems> deleteCartItem(@PathVariable("itemId") Long itemId) {
        Optional<CartItems> deletedItem = cartItemService.deleteCartItem(itemId);
        if (deletedItem.isPresent()) {
            return ResponseEntity.ok(deletedItem.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}