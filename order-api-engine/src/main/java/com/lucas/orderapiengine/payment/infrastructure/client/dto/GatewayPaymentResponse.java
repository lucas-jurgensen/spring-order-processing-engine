package com.lucas.orderapiengine.payment.infrastructure.client.dto;

import java.util.UUID;

public record GatewayPaymentResponse(
    UUID orderId,
    String status
) {}
