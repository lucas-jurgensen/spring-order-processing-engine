package com.lucas.fakepaymentgateway.payment.domain.rules;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lucas.fakepaymentgateway.payment.domain.model.Payment;
import com.lucas.fakepaymentgateway.payment.domain.rules.interfaces.PaymentRule;

@Component
@Order(999)
public class DefaultApprovalRule implements PaymentRule {

    @Override
    public boolean matches(Payment payment) {
        return true;
    }

    @Override
    public void apply(Payment payment) {
        payment.approve();
    }

    @Override
    public long delayMs() {
        return 1000;
    }
}
