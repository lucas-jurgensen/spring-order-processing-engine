package com.lucas.orderapiengine.payment.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.lucas.orderapiengine.payment.infrastructure.client.dto.GatewayPaymentRequest;
import com.lucas.orderapiengine.payment.infrastructure.client.dto.GatewayPaymentResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentGatewayClient {
    
    private final RestClient restClient;

    @Value("${gateway.url}")
    private String gatewayUrl;

    public GatewayPaymentResponse process(GatewayPaymentRequest request) {
        return restClient.post()
            .uri(gatewayUrl + "/api/paymentss")
            .body(request)
            .retrieve()
            .body(GatewayPaymentResponse.class);
    }
}
