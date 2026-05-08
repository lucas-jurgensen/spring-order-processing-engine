package com.lucas.orderapiengine.delivering.infrastructure.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.lucas.orderapiengine._shared.infrastructure.kafka.topics.KafkaTopics;
import com.lucas.orderapiengine.delivering.application.service.DeliveringService;
import com.lucas.orderapiengine.shipping.domain.event.OrderShippedEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeliveringConsumer {
    private final DeliveringService deliveringService;

    @KafkaListener(topics = KafkaTopics.ORDER_SHIPPED, groupId = "delivering-group")
    public void consume(OrderShippedEvent event) {
        deliveringService.process(event);
    }
}
