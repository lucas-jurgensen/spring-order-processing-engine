package com.lucas.orderapiengine.payment.domain.event;

import java.util.UUID;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;

public class PaymentFailedEvent implements DomainEvent {
    private UUID orderId;
    
    public PaymentFailedEvent() {
    }

    public PaymentFailedEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
