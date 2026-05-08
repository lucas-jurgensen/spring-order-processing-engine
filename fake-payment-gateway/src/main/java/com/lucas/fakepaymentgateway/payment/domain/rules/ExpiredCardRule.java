package com.lucas.fakepaymentgateway.payment.domain.rules;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lucas.fakepaymentgateway.payment.domain.model.Payment;
import com.lucas.fakepaymentgateway.payment.domain.rules.interfaces.PaymentRule;

@Component
@Order(4)
public class ExpiredCardRule implements PaymentRule {

    @Override
    public boolean matches(Payment payment) {
        return payment.getCard().isExpired();
    }

    @Override
    public void apply(Payment payment) {
        payment.fail();
    }

    @Override
    public long delayMs() {
        return 2000;
    }
}
