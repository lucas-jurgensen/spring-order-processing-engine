package com.lucas.orderapiengine.order.domain.event;

import java.util.UUID;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;

public class OrderCreatedEvent implements DomainEvent {
    private UUID orderId;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
