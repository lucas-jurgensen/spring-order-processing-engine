package com.lucas.ordersystem.order.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.lucas.ordersystem.order.domain.enums.OrderStatus;
import com.lucas.ordersystem.order.domain.event.OrderCreatedEvent;
import com.lucas.ordersystem.order.domain.event.PaymentApprovedEvent;
import com.lucas.ordersystem.order.domain.event.interfaces.DomainEvent;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Order {

    @Id
    private UUID id;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Transient
    private List<DomainEvent> events = new ArrayList<>();

    public static Order create(List<OrderItem> items) {
        Order order = new Order();

        order.id = UUID.randomUUID();
        order.status = OrderStatus.PAYMENT_PENDING;
        order.createdAt = LocalDateTime.now();
        order.items = items;
        order.totalAmount = order.calculateTotalAmount();

        order.addEvent(new OrderCreatedEvent(order.id));

        return order;
    }

    public void approvePayment() {
        if (this.status != OrderStatus.PAYMENT_PENDING) {
            throw new IllegalStateException("Estado de evento Inválido.");
        }

        this.status = OrderStatus.PAYMENT_APPROVED;

        this.addEvent(new PaymentApprovedEvent(this.id));
    }

    private void addEvent(DomainEvent event) {
        this.events.add(event);
    }

    public List<DomainEvent> getEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }

    private BigDecimal calculateTotalAmount() {
        return items.stream()
            .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
