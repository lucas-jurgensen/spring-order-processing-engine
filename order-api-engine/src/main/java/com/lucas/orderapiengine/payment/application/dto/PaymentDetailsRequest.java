package com.lucas.orderapiengine.payment.application.dto;

import jakarta.validation.constraints.Pattern;

public record PaymentDetailsRequest(
    String holderName,
    String number,
    String cvv,
    @Pattern(
    regexp = "^(0[1-9]|1[0-2])\\/\\d{4}$",
    message = "Expiração deve estar no formato MM/YYYY"
    )
    String expiration
) {}
