package com.sfey.microservice.controller;

import com.sfey.microservice.service.domain.Payment;
import com.sfey.microservice.service.domain.PaymentResult;
import com.sfey.microservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<PaymentResult> processPayment(@RequestBody @Valid Payment payment) {
        PaymentResult paymentResult = paymentService.processPayment(payment);
        return ResponseEntity.ok(paymentResult);
    }

}
