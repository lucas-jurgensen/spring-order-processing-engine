package com.lucas.orderapiengine.shipping.domain.event;

import java.util.UUID;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;

public class OrderShippedEvent implements DomainEvent {
    private UUID orderId;

    public OrderShippedEvent() {
    }

    public OrderShippedEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
