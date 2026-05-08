package com.lucas.orderapiengine.order.application.dto;

import java.util.List;

import com.lucas.orderapiengine.payment.application.dto.PaymentDetailsRequest;

public record CreateOrderRequest(
    List<CreateOrderItemRequest> items,
    PaymentDetailsRequest payment
) {}
