package com.lucas.fakepaymentgateway.payment.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.lucas.fakepaymentgateway.payment.domain.model.Card;

public record PaymentRequest(
    UUID orderId,
    BigDecimal amount,
    Card card
) {}
