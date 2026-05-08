package com.lucas.orderapiengine.order.domain.event;

import java.math.BigDecimal;
import java.util.UUID;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;
import com.lucas.orderapiengine.order.domain.model.OrderPaymentDetails;

public class OrderCreatedEvent implements DomainEvent {
    private UUID orderId;
    private BigDecimal totalAmount;
    private OrderPaymentDetails paymentDetails;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(UUID orderId, BigDecimal totalAmount, OrderPaymentDetails paymentDetails) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.paymentDetails = paymentDetails;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderPaymentDetails getPaymentDetails() {
        return paymentDetails;
    }
}
