package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.model.OrderItem;
import com.example.cosmetic_be.repository.IOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders/items")
public class OrderItemsController {
    @Autowired
    private IOrderItemRepository iOrderItemRepository;
    @GetMapping("/{orderId}")
    public ResponseEntity<Iterable<OrderItem>>findOrdersByOrderId(@PathVariable Long orderId){
        Iterable<OrderItem> orderItems=iOrderItemRepository.findByOrderId(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }
    @GetMapping("/total-sold")
    public Integer getTotalQuantitySold() {
        return iOrderItemRepository.findTotalQuantitySold();
    }
    @GetMapping("/top-selling-products")
    public ResponseEntity<List<Object[]>> getTopSellingProducts() {
        List<Object[]> topSellingProducts = iOrderItemRepository.findTopSellingProducts();
        return ResponseEntity.ok(topSellingProducts);
    }
}
