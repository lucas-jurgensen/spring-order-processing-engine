package com.lucas.fakepaymentgateway.payment.domain.model;

import java.time.LocalDate;

public record Card(
    String holderName,
    String number,
    String cvv,
    LocalDate expiration
) {

    public boolean isExpired() {
        return expiration.isBefore(LocalDate.now());
    }
}