package com.lucas.fakepaymentgateway.payment.domain.rules;

import org.springframework.core.annotation.Order;

import com.lucas.fakepaymentgateway.payment.domain.model.Payment;
import com.lucas.fakepaymentgateway.payment.domain.rules.interfaces.PaymentRule;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Order(3)
public class InvalidCvvRule implements PaymentRule {

    @Override
    public boolean matches(Payment payment) {
        String cvv = payment.getCard().cvv();
        return cvv == null || !cvv.matches("^\\d{3,4}$");
    }

    @Override
    public void apply(Payment payment) {
        payment.fail();
    }

    public long delayMs() {
        return 300;
    }
}
