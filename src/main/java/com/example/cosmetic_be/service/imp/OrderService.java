package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.dto.OrderDTO;
import com.example.cosmetic_be.dto.OrderItemDTO;
import com.example.cosmetic_be.dto.ProductDTO;
import com.example.cosmetic_be.dto.VariantDTO;
import com.example.cosmetic_be.model.*;
import com.example.cosmetic_be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private IAccountsRepository iAccountsRepository;
    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IOrderItemRepository iOrderItemRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IProductVariantsRepository iProductVariantsRepository;

    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        Accounts account = iAccountsRepository.findById(orderDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));


        Order order = new Order();
        order.setAccount(account);
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.setPaymentStatus("Đang xử lý");
        order.setShippingStatus("Đang xử lý");


        Order savedOrder = iOrderRepository.save(order);
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO itemRequest : orderDTO.getItems()) {
            OrderItem orderItem = new OrderItem();

            Products product = iProductRepository.findById(itemRequest.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

            ProductVariants variant = null;
            if (itemRequest.getVariant() != null && itemRequest.getVariant().getId() != null) {
                variant = iProductVariantsRepository.findById(itemRequest.getVariant().getId())
                        .orElseThrow(() -> new RuntimeException("Biến thể không tồn tại"));
            }

            orderItem.setProduct(product);
            orderItem.setVariant(variant);
//            tính tổng giá của sp

            BigDecimal unitPrice = product.getPrice();
            BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(totalPrice);
            orderItem.setOrder(savedOrder);
            orderItems.add(orderItem);
            int newQuantity = product.getQuantity() - itemRequest.getQuantity();
            if (newQuantity < 0) {
                throw new RuntimeException("Sản phẩm không đủ số lượng trong kho");
            }
            product.setQuantity(newQuantity);
            iProductRepository.save(product);
        }
        iOrderItemRepository.saveAll(orderItems);
    }

}
