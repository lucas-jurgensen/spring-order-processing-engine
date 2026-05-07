package com.lucas.orderapiengine._shared.infrastructure.kafka.publisher;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;
import com.lucas.orderapiengine._shared.infrastructure.kafka.topics.KafkaTopics;
import com.lucas.orderapiengine.order.domain.event.OrderCreatedEvent;
import com.lucas.orderapiengine.payment.domain.event.PaymentApprovedEvent;

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

        throw new IllegalArgumentException(
            "Nenhum tópico encontrado para o evento: "
                + event.getClass().getSimpleName()
        );
    }
}
