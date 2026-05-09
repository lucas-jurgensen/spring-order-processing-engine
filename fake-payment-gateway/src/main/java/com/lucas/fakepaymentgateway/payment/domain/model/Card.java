package com.lucas.fakepaymentgateway.payment.domain.model;

import java.time.YearMonth;

public record Card(
    String holderName,
    String number,
    String cvv,
    String expiration
) {
    private static final String EXPIRATION_REGEX =
        "^(0[1-9]|1[0-2])\\/\\d{4}$";

    public Card {
        if (!expiration.matches(EXPIRATION_REGEX)) {
            throw new IllegalArgumentException(
                "Formato de expiração inválido."
            );
        }
    }

    public boolean isExpired() {
        String[] parts = expiration.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);

        YearMonth expirationDate = YearMonth.of(year, month);

        return expirationDate.isBefore(YearMonth.now());
    }
}