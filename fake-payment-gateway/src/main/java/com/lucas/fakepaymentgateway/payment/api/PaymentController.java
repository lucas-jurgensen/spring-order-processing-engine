package com.lucas.fakepaymentgateway.payment.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.fakepaymentgateway.payment.application.dto.PaymentRequest;
import com.lucas.fakepaymentgateway.payment.application.dto.PaymentResponse;
import com.lucas.fakepaymentgateway.payment.application.service.PaymentService;
import com.lucas.fakepaymentgateway.payment.domain.model.Payment;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse pay(@RequestBody PaymentRequest request) {
        Payment payment = Payment.from(request);

        return paymentService.process(payment);
    }
}
