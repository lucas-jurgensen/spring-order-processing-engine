package com.lucas.orderapiengine._shared.infrastructure.kafka.publisher;

import java.util.List;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;

public interface EventPublisher {
    void publish(List<DomainEvent> events);
}
