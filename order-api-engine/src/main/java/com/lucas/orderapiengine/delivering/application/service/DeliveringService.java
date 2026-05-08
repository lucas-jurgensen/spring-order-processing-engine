package com.lucas.orderapiengine.delivering.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.orderapiengine._shared.infrastructure.kafka.publisher.EventPublisher;
import com.lucas.orderapiengine.delivering.domain.event.OrderDeliveredEvent;
import com.lucas.orderapiengine.shipping.domain.event.OrderShippedEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveringService {
    private final EventPublisher publisher;

    public void process(OrderShippedEvent event) {
        publisher.publish(List.of(new OrderDeliveredEvent(event.getOrderId())));
    }    
}
