package com.lucas.orderapiengine.stock.domain.event;

import java.util.UUID;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;

public class StockReservedEvent implements DomainEvent {
    private UUID orderId;

    public StockReservedEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public StockReservedEvent() {
    }

    public UUID getOrderId() {
        return orderId;
    }
}
