package com.lucas.orderapiengine.stock.application.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.lucas.orderapiengine._shared.infrastructure.kafka.publisher.EventPublisher;
import com.lucas.orderapiengine.payment.domain.event.PaymentApprovedEvent;
import com.lucas.orderapiengine.stock.domain.event.StockReservedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
    private final EventPublisher publisher;

    public void process(PaymentApprovedEvent event) {
        publisher.publish(List.of(new StockReservedEvent(event.getOrderId())));
    }
}
