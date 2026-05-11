package com.lucas.fakepaymentgateway.payment.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.lucas.fakepaymentgateway.payment.application.dto.PaymentRequest;
import com.lucas.fakepaymentgateway.payment.domain.enums.PaymentStatus;

public class Payment {
    private UUID id;
    private UUID orderId;
    private BigDecimal amount;
    private PaymentStatus status;
    private Card card;

    public Payment(UUID id, UUID orderId, BigDecimal amount, Card card) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
        this.card = card;
    }

     public static Payment from(PaymentRequest request) {
        return new Payment(
            UUID.randomUUID(),
            request.orderId(),
            request.amount(),
            request.card()
        );
    }

    public void approve() {
        this.status = PaymentStatus.APPROVED;
    }

    public void fail() {
        this.status = PaymentStatus.FAILED;
    }

    public void review() {
        this.status = PaymentStatus.PENDING_REVIEW;
    }

    public void fraud() {
        this.status = PaymentStatus.FRAUD_SUSPECTED;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Card getCard() {
        return card;
    }    
}
