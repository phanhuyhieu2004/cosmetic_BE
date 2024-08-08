package com.example.cosmetic_be.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderDTO {
    private Long accountId;
    private BigDecimal totalPrice;
    private List<OrderItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(Long accountId, BigDecimal totalPrice, List<OrderItemDTO> items) {
        this.accountId = accountId;
        this.totalPrice = totalPrice;
        this.items = items;
    }
}
