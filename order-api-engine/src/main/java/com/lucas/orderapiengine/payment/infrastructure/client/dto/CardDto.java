package com.lucas.orderapiengine.payment.infrastructure.client.dto;

import java.time.LocalDate;

import com.lucas.orderapiengine.order.domain.model.OrderPaymentDetails;

public record CardDto(
    String holderName,
    String number,
    String cvv,
    LocalDate expiration
) {
    public static CardDto from(
        OrderPaymentDetails paymentDetails
    ) {

        return new CardDto(
            paymentDetails.getHolderName(),
            paymentDetails.getCardNumber(),
            paymentDetails.getCvv(),
            paymentDetails.getExpiration()
        );
    }
}
