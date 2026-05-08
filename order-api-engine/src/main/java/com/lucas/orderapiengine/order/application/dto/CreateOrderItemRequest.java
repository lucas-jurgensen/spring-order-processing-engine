package com.lucas.orderapiengine.order.application.dto;

import java.math.BigDecimal;

public record CreateOrderItemRequest(
    String productId,
    Integer quantity,
    BigDecimal price
) {}
