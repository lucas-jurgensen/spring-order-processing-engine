package com.lucas.fakepaymentgateway.payment.domain.engine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.fakepaymentgateway.payment.domain.enums.PaymentStatus;
import com.lucas.fakepaymentgateway.payment.domain.model.Payment;
import com.lucas.fakepaymentgateway.payment.domain.rules.interfaces.PaymentRule;

@Service
public class PaymentRuleEngine {
    private final List<PaymentRule> rules;

    public PaymentRuleEngine(List<PaymentRule> rules) {
        this.rules = rules;
    }

    public PaymentStatus evaluate(Payment payment) {
        for (PaymentRule rule : rules) {
            if (rule.matches(payment)) {
                sleep(rule.delayMs());
                rule.apply(payment);

                return payment.getStatus();
            }
        }

        return PaymentStatus.APPROVED;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
