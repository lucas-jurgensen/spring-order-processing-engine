package com.lucas.orderapiengine.delivering.domain.event;

import java.util.UUID;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;

public class OrderDeliveredEvent implements DomainEvent {
    private UUID orderId;

    public OrderDeliveredEvent() {
    }

    public OrderDeliveredEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
