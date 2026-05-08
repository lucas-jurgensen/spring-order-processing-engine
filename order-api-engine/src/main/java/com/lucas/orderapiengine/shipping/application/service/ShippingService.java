package com.lucas.orderapiengine.shipping.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.orderapiengine._shared.infrastructure.kafka.publisher.EventPublisher;
import com.lucas.orderapiengine.shipping.domain.event.OrderShippedEvent;
import com.lucas.orderapiengine.stock.domain.event.StockReservedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingService {
    private final EventPublisher publisher;
    
    public void process(StockReservedEvent event) {
        publisher.publish(List.of(new OrderShippedEvent(event.getOrderId())));
    }
}
