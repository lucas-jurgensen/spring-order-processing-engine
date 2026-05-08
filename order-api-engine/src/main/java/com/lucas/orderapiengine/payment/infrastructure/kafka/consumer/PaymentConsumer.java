package com.lucas.orderapiengine.payment.infrastructure.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.lucas.orderapiengine._shared.infrastructure.kafka.topics.KafkaTopics;
import com.lucas.orderapiengine.order.domain.event.OrderCreatedEvent;
import com.lucas.orderapiengine.payment.application.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentService paymentService;

    @KafkaListener(topics = KafkaTopics.ORDER_CREATED, groupId = "payment-group")
    public void consume(OrderCreatedEvent event) {
        paymentService.process(event);
    }
}
