package com.lucas.fakepaymentgateway.payment.domain.rules.interfaces;

import com.lucas.fakepaymentgateway.payment.domain.model.Payment;

public interface PaymentRule {
    boolean matches(Payment payment);
    
    void apply(Payment payment);

    default long delayMs() {
        return 0;
    }
}
