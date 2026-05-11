package com.lucas.fakepaymentgateway.payment.domain.rules;

import java.math.BigDecimal;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lucas.fakepaymentgateway.payment.domain.model.Payment;
import com.lucas.fakepaymentgateway.payment.domain.rules.interfaces.PaymentRule;

@Component
@Order(5)
public class HighAmountRule implements PaymentRule {

    @Override
    public boolean matches(Payment payment) {
        return payment.getAmount().compareTo(BigDecimal.valueOf(10000)) > 0;
    }

    @Override
    public void apply(Payment payment) {
        payment.review();
    }

    @Override
    public long delayMs() {
        return 5000;
    }
}
