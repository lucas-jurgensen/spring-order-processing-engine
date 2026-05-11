package com.lucas.orderapiengine._shared.infrastructure.kafka.publisher;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;
import com.lucas.orderapiengine._shared.infrastructure.kafka.topics.KafkaTopics;
import com.lucas.orderapiengine.delivering.domain.event.OrderDeliveredEvent;
import com.lucas.orderapiengine.order.domain.event.OrderCreatedEvent;
import com.lucas.orderapiengine.payment.domain.event.PaymentApprovedEvent;
import com.lucas.orderapiengine.payment.domain.event.PaymentFailedEvent;
import com.lucas.orderapiengine.shipping.domain.event.OrderShippedEvent;
import com.lucas.orderapiengine.stock.domain.event.StockReservedEvent;

@Component
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(List<DomainEvent> events) {

        for (DomainEvent event : events) {
            String topic = resolveTopic(event);
            kafkaTemplate.send(topic, event);
        }
    }

    private String resolveTopic(DomainEvent event) {
        if (event instanceof OrderCreatedEvent) {
            return KafkaTopics.ORDER_CREATED;
        }

        if (event instanceof PaymentApprovedEvent) {
            return KafkaTopics.PAYMENT_APPROVED;
        }

        if (event instanceof PaymentFailedEvent) {
            return KafkaTopics.PAYMENT_FAILED;
        }

        if (event instanceof StockReservedEvent) {
            return KafkaTopics.STOCK_RESERVED;
        }

        if (event instanceof OrderShippedEvent) {
            return KafkaTopics.ORDER_SHIPPED;
        }

        if (event instanceof OrderDeliveredEvent) {
            return KafkaTopics.ORDER_DELIVERED;
        }

        throw new IllegalArgumentException(
            "Nenhum tópico encontrado para o evento: "
                + event.getClass().getSimpleName()
        );
    }
}
