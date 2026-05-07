package com.lucas.orderapiengine.order.infrastructure.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.lucas.orderapiengine._shared.infrastructure.kafka.topics.KafkaTopics;
import com.lucas.orderapiengine.order.application.service.OrderService;
import com.lucas.orderapiengine.payment.domain.event.PaymentApprovedEvent;
import com.lucas.orderapiengine.payment.domain.event.PaymentFailedEvent;
import com.lucas.orderapiengine.shipping.domain.event.OrderShippedEvent;
import com.lucas.orderapiengine.stock.domain.event.StockReservedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderConsumer {
    private final OrderService orderService;

    @KafkaListener(topics = KafkaTopics.PAYMENT_APPROVED, groupId = "order-group")
    public void onPaymentApproved(PaymentApprovedEvent event) {
        orderService.markPaymentApproved(event.getOrderId());
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_FAILED, groupId = "order-group")
    public void onPaymentFailed(PaymentFailedEvent event) {
        orderService.markPaymentFailed(event.getOrderId());
    }

    @KafkaListener(topics = KafkaTopics.STOCK_RESERVED, groupId = "order-group")
    public void onStockReserved(StockReservedEvent event) {
        orderService.markStockReserved(event.getOrderId());
    }

    @KafkaListener(topics = KafkaTopics.ORDER_SHIPPED, groupId = "order-group")
    public void onOrderShipped(OrderShippedEvent event) {
        orderService.markShippedOrder(event.getOrderId());
    }
}
