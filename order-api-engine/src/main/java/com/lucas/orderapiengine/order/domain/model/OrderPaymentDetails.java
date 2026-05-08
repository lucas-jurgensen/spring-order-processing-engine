package com.lucas.orderapiengine.order.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderPaymentDetails {
    private String holderName;
    private String cardNumber;
    private String cvv;
    private LocalDate expiration;

    protected OrderPaymentDetails() {}

    public OrderPaymentDetails(
        String holderName,
        String cardNumber,
        String cvv,
        LocalDate expiration
    ) {
        validate(holderName, cardNumber, cvv, expiration);

        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiration = expiration;
    }

    private void validate(
        String holderName,
        String cardNumber,
        String cvv,
        LocalDate expiration
    ) {

        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Número do cartão inválido.");
        }

        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("CVV inválido.");
        }

        if (expiration == null) {
            throw new IllegalArgumentException("Expiração inválida.");
        }
    }

    public String getHolderName() {
        return holderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public String getMaskedNumber() {
        return "**** **** **** " +
            cardNumber.substring(cardNumber.length() - 4);
    }
}
