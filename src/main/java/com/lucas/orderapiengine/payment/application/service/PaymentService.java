package com.lucas.orderapiengine.payment.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.orderapiengine._shared.infrastructure.kafka.publisher.EventPublisher;
import com.lucas.orderapiengine.order.domain.event.OrderCreatedEvent;
import com.lucas.orderapiengine.payment.domain.event.PaymentApprovedEvent;
import com.lucas.orderapiengine.payment.domain.event.PaymentFailedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final EventPublisher publisher;

    public void process(OrderCreatedEvent event) {
        boolean approved = simulatePayment();

        if (approved) {
            publisher.publish(
                List.of(
                    new PaymentApprovedEvent(event.getOrderId()
                )));

                return;
        }

        publisher.publish(
            List.of(
                new PaymentFailedEvent(event.getOrderId())
            )
        );
    }

    private boolean simulatePayment() {
        return Math.random() > 0.2;
    }
}
