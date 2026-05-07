package com.lucas.ordersystem.order.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class OrderItem {

    private String productId;
    private Integer quantity;
    private BigDecimal price;

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
