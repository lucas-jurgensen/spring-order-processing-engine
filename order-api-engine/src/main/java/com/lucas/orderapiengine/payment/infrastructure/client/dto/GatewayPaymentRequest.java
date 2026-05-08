package com.lucas.orderapiengine.payment.infrastructure.client.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record GatewayPaymentRequest(
    UUID orderId,
    BigDecimal amount,
    CardDto card
) {}
