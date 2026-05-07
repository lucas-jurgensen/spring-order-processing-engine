package com.lucas.ordersystem.order.domain.event;

import java.util.UUID;

import com.lucas.ordersystem.order.domain.event.interfaces.DomainEvent;

public class OrderCreatedEvent implements DomainEvent {
    private final UUID orderId;

    public OrderCreatedEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
