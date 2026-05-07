package com.lucas.orderapiengine.order.application.dto;

import java.util.List;

public record CreateOrderRequest(
    List<CreateOrderItemRequest> items
) {}
