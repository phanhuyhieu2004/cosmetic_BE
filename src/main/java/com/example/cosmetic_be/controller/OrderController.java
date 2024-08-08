package com.example.cosmetic_be.controller;

import com.example.cosmetic_be.dto.OrderDTO;
import com.example.cosmetic_be.model.Order;
import com.example.cosmetic_be.repository.ICategoriesRepository;
import com.example.cosmetic_be.repository.IOrderRepository;
import com.example.cosmetic_be.service.imp.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
@Autowired
private IOrderRepository iOrderRepository;
    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            // Gọi dịch vụ để tạo đơn hàng
            orderService.createOrder(orderDTO);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
@GetMapping("")
    public ResponseEntity<Iterable<Order>> getAllOrders(){
        Iterable<Order> orders=iOrderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
}

}