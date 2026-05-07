package com.lucas.orderapiengine.order.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.orderapiengine._shared.infrastructure.kafka.publisher.EventPublisher;
import com.lucas.orderapiengine.order.application.dto.CreateOrderRequest;
import com.lucas.orderapiengine.order.domain.model.Order;
import com.lucas.orderapiengine.order.domain.model.OrderItem;
import com.lucas.orderapiengine.order.infrastructure.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final EventPublisher publisher;

    @Transactional
    public UUID create(CreateOrderRequest request) {
        List<OrderItem> items = 
            request.items()
                .stream()
                .map(i -> new OrderItem(
                    i.productId(),
                    i.quantity(),
                    i.price()
                ))
                .toList();

        Order order = Order.create(items);
        orderRepository.save(order);
        publisher.publish(order.getEvents());
        order.clearEvents();

        return order.getId();
    }

    @Transactional
    public void markPaymentApproved(UUID orderId) {
        Order order = findOrderById(orderId);

        order.onPaymentApproved();
        orderRepository.save(order);
    }

    @Transactional
    public void markPaymentFailed(UUID orderId) {
        Order order = findOrderById(orderId);

        order.onPaymentFailed();
        orderRepository.save(order);
    }

    @Transactional
    public void markStockReserved(UUID orderId) {
        Order order = findOrderById(orderId);

        order.reserveStock();
        orderRepository.save(order);
    }

    public void markShippedOrder(UUID orderId) {
        Order order = findOrderById(orderId);
        
        order.ship();
        orderRepository.save(order);
    } 

    private Order findOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(); 
    }
}
