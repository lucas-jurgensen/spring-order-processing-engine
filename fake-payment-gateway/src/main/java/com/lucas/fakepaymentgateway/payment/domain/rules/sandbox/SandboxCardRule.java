package com.lucas.fakepaymentgateway.payment.domain.rules.sandbox;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lucas.fakepaymentgateway.payment.domain.enums.PaymentStatus;
import com.lucas.fakepaymentgateway.payment.domain.model.Payment;
import com.lucas.fakepaymentgateway.payment.domain.rules.interfaces.PaymentRule;

@Component
@Order(1)
public class SandboxCardRule implements PaymentRule {

    @Override
    public boolean matches(Payment payment) {

        return SandboxCardRegistry.CARDS
            .containsKey(payment.getCard().number());
    }

    @Override
    public void apply(Payment payment) {

        PaymentStatus status =
            SandboxCardRegistry.CARDS
                .get(payment.getCard().number());

        switch (status) {

            case APPROVED -> payment.approve();

            case FAILED -> payment.fail();

            case PENDING_REVIEW -> payment.review();

            case FRAUD_SUSPECTED -> payment.fraud();

            default -> throw new IllegalArgumentException("Status de pagamento inválido: " + status);
        }
    }

    @Override
    public long delayMs() {
        return 1500;
    }
}
