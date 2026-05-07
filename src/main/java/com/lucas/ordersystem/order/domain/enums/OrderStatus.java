package com.lucas.ordersystem.order.domain.enums;

public enum OrderStatus {
    CREATED,
    PAYMENT_PENDING,
    PAYMENT_APPROVED,
    PAYMENT_FAILED,
    STOCK_RESERVED,
    STOCK_FAILED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
