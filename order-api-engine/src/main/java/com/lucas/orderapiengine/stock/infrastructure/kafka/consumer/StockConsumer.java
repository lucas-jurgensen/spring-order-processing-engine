package com.lucas.orderapiengine.stock.infrastructure.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.lucas.orderapiengine._shared.infrastructure.kafka.topics.KafkaTopics;
import com.lucas.orderapiengine.payment.domain.event.PaymentApprovedEvent;
import com.lucas.orderapiengine.stock.application.service.StockService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StockConsumer {
    private final StockService stockService;

    @KafkaListener(topics = KafkaTopics.PAYMENT_APPROVED, groupId = "stock-group")
    public void paymentApproved(PaymentApprovedEvent event) {
        stockService.process(event);
    }
}
