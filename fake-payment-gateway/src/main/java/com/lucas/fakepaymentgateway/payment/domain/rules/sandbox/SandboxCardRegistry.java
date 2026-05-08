package com.lucas.fakepaymentgateway.payment.domain.rules.sandbox;

import java.util.Map;

import com.lucas.fakepaymentgateway.payment.domain.enums.PaymentStatus;

public class SandboxCardRegistry {
    public static final Map<String, PaymentStatus> CARDS =
        Map.of(
            "4111111111111111", PaymentStatus.APPROVED,
            "4000000000000002", PaymentStatus.FAILED,
            "4000000000009995", PaymentStatus.FRAUD_SUSPECTED,
            "5555555555554444", PaymentStatus.PENDING_REVIEW
        );

    private SandboxCardRegistry() {}
}
