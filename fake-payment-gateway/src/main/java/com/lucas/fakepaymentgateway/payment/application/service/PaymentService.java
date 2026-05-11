package com.lucas.fakepaymentgateway.payment.application.service;

import org.springframework.stereotype.Service;

import com.lucas.fakepaymentgateway.payment.application.dto.PaymentResponse;
import com.lucas.fakepaymentgateway.payment.domain.engine.PaymentRuleEngine;
import com.lucas.fakepaymentgateway.payment.domain.enums.PaymentStatus;
import com.lucas.fakepaymentgateway.payment.domain.model.Payment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRuleEngine engine;
    
    public PaymentResponse process(Payment payment) {
        PaymentStatus status = engine.evaluate(payment);

        return new PaymentResponse(
            payment.getOrderId(),
            status
        );
    }

}
