package com.lucas.orderapiengine.order.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.lucas.orderapiengine._shared.domain.event.DomainEvent;
import com.lucas.orderapiengine.order.domain.enums.OrderStatus;
import com.lucas.orderapiengine.order.domain.event.OrderCreatedEvent;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    @Transient
    private List<DomainEvent> events = new ArrayList<>();

    protected Order() {}

    public static Order create(List<OrderItem> items) {
        validateItems(items);
        
        Order order = new Order();

        order.id = UUID.randomUUID();
        order.status = OrderStatus.PAYMENT_PENDING;
        order.createdAt = LocalDateTime.now();
        order.items = items;
        order.totalAmount = order.calculateTotalAmount();

        order.addEvent(new OrderCreatedEvent(order.id));

        return order;
    }

    public void onPaymentApproved() {
        ensureStatus(OrderStatus.PAYMENT_PENDING);

        this.status = OrderStatus.PAYMENT_APPROVED;
    }

    public void onPaymentFailed() {
        ensureStatus(OrderStatus.PAYMENT_PENDING);

        this.status = OrderStatus.PAYMENT_FAILED;
    }

    public void reserveStock() {
        ensureStatus(OrderStatus.PAYMENT_APPROVED);

        this.status = OrderStatus.STOCK_RESERVED;
    }

    public void ship() {
        ensureStatus(OrderStatus.STOCK_RESERVED);

        this.status = OrderStatus.SHIPPED;
    }

    public void deliver() {
        ensureStatus(OrderStatus.SHIPPED);

        this.status = OrderStatus.DELIVERED;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELED;
    }

    private void addEvent(DomainEvent event) {
        this.events.add(event);
    }

    public List<DomainEvent> getEvents() {
        return List.copyOf(events);
    }

    public void clearEvents() {
        this.events.clear();
    }

    private void ensureStatus(OrderStatus expected) {
        if (this.status != expected) {
            throw new IllegalArgumentException("Estado de evento inválido.");
        }
    }

    private BigDecimal calculateTotalAmount() {
        return items.stream()
            .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void validateItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("É necessário adicionar itens para fazer o pedido.");
        }
    }

    public UUID getId() {
        return id;
    }
}
