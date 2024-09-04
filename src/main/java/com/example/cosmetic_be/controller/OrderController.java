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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
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
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            // Gọi dịch vụ để tạo đơn hàng
            orderService.createOrder(orderDTO);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
@GetMapping("")
    public ResponseEntity<Iterable<Order>> getAllOrders(){
        Iterable<Order> orders=iOrderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
}

    @GetMapping("/daily")
    public ResponseEntity<?> getDailyRevenue(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        BigDecimal revenue = orderService.getRevenueByDate(localDate);
        return ResponseEntity.ok(Collections.singletonMap("totalRevenue", revenue));
    }

    @GetMapping("/monthly")
    public ResponseEntity<?> getMonthlyRevenue(@RequestParam int month, @RequestParam int year) {
        BigDecimal revenue = orderService.getRevenueByMonth(month, year);
        return ResponseEntity.ok(Collections.singletonMap("totalRevenue", revenue));
    }

    @GetMapping("/yearly")
    public ResponseEntity<?> getYearlyRevenue(@RequestParam int year) {
        BigDecimal revenue = orderService.getRevenueByYear(year);
        return ResponseEntity.ok(Collections.singletonMap("totalRevenue", revenue));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrderStatus(id, order.getPaymentStatus(), order.getShippingStatus());
        return new ResponseEntity(updatedOrder,HttpStatus.OK);
    }
    @GetMapping("/count-order-status")
    public Object[] countOrdersBySpecificStatuses() {
        return orderService.getCountOrdersBySpecificStatuses();
    }
}