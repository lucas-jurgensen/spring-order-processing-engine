package com.lucas.ordersystem.order.domain.event;

import java.util.UUID;

import com.lucas.ordersystem.order.domain.event.interfaces.DomainEvent;

public class PaymentApprovedEvent implements DomainEvent {
    private final UUID orderId;

    public PaymentApprovedEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
