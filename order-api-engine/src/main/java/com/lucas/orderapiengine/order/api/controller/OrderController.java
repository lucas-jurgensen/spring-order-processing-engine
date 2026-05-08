package com.lucas.orderapiengine.order.api.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.orderapiengine.order.application.dto.CreateOrderRequest;
import com.lucas.orderapiengine.order.application.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateOrderRequest request) {
        UUID orderId = orderService.create(request);

        return ResponseEntity.ok(
            "Pedido feito como sucesso: " + orderId
        );
    }
}
