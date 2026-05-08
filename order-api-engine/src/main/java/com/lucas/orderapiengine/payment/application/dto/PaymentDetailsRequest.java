package com.lucas.orderapiengine.payment.application.dto;

import java.time.LocalDate;

public record PaymentDetailsRequest(
    String holderName,
    String number,
    String cvv,
    LocalDate expiration
) {}
