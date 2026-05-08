package com.lucas.orderapiengine.shipping.infrastructure.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.lucas.orderapiengine._shared.infrastructure.kafka.topics.KafkaTopics;
import com.lucas.orderapiengine.shipping.application.service.ShippingService;
import com.lucas.orderapiengine.stock.domain.event.StockReservedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShippingConsumer {
    private final ShippingService shippingService;

    @KafkaListener(topics = KafkaTopics.STOCK_RESERVED, groupId = "shipping-group")
    public void reserveStockApproved(StockReservedEvent event) {
        shippingService.process(event);
    }
}
