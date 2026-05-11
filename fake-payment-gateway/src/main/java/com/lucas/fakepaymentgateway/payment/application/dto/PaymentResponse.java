package com.lucas.fakepaymentgateway.payment.application.dto;

import java.util.UUID;

import com.lucas.fakepaymentgateway.payment.domain.enums.PaymentStatus;

public record PaymentResponse(
    UUID orderId,
    PaymentStatus status
) {}
