package com.lucas.fakepaymentgateway.payment.domain.rules;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lucas.fakepaymentgateway.payment.domain.model.Payment;
import com.lucas.fakepaymentgateway.payment.domain.rules.interfaces.PaymentRule;

@Component
@Order(2)
public class InvalidCardNumberRule implements PaymentRule{

    @Override
    public boolean matches(Payment payment) {
        String number = payment.getCard().number();
        return !isValidLuhn(number);
    }

    @Override
    public void apply(Payment payment) {
        payment.fail();
    }

    @Override
    public long delayMs() {
        return 2500;
    }

    private boolean isValidLuhn(String cardNumber) {
        if (cardNumber == null || !cardNumber.matches("\\d+")) {
            return false;
        }

        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(cardNumber.charAt(i));

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }
}
