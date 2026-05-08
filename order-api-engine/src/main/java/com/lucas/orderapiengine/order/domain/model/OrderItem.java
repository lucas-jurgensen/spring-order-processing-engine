package com.lucas.orderapiengine.order.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id")    
    private String productId;
    private Integer quantity;
    private BigDecimal price;

    protected OrderItem() {}

    public OrderItem(
        String productId,
        Integer quantity,
        BigDecimal price
    ) {
        validate(quantity, price);

        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    private void validate(Integer quantity, BigDecimal price) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantidade de itens inválida.");
        }

        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço inválido.");
        }
    }
    
    public BigDecimal subtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
