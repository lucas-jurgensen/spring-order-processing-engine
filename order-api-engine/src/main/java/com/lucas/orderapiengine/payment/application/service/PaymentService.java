package com.lucas.orderapiengine.payment.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.orderapiengine._shared.infrastructure.kafka.publisher.EventPublisher;
import com.lucas.orderapiengine.order.domain.event.OrderCreatedEvent;
import com.lucas.orderapiengine.payment.domain.event.PaymentApprovedEvent;
import com.lucas.orderapiengine.payment.domain.event.PaymentFailedEvent;
import com.lucas.orderapiengine.payment.infrastructure.client.PaymentGatewayClient;
import com.lucas.orderapiengine.payment.infrastructure.client.dto.CardDto;
import com.lucas.orderapiengine.payment.infrastructure.client.dto.GatewayPaymentRequest;
import com.lucas.orderapiengine.payment.infrastructure.client.dto.GatewayPaymentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentGatewayClient client;
    private final EventPublisher publisher;

    public void process(OrderCreatedEvent event) {
        GatewayPaymentRequest request =
            new GatewayPaymentRequest(
                event.getOrderId(),
                event.getTotalAmount(),
                CardDto.from(event.getPaymentDetails())
            );

        GatewayPaymentResponse response = client.process(request);

        switch (response.status()) {
            case "APPROVED":
                publisher.publish(List.of(
                    new PaymentApprovedEvent(response.orderId())
                ));
                break;
        
            case "FAILED":
                publisher.publish(List.of(
                    new PaymentFailedEvent(response.orderId())
                ));
                break;

            default:
                break;
        }
    }
}
