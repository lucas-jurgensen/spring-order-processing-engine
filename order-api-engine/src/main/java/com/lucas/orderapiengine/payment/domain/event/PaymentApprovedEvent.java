package com.lucas.orderapiengine.payment.domain.event;

import java.util.UUID;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;

public class PaymentApprovedEvent implements DomainEvent {
    private UUID orderId;

    public PaymentApprovedEvent() {
    }

    public PaymentApprovedEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
