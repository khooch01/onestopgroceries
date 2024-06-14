package com.khooch.onestopgroceries.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khooch.onestopgroceries.service.PaymentProcessor;
import com.khooch.onestopgroceries.service.PaymentProcessorFactory;

@RestController
public class PaymentController {

    private final PaymentProcessorFactory paymentProcessorFactory = new PaymentProcessorFactory();

    @GetMapping("/process-payment")
    public String processPayment(@RequestParam String mode) {
        PaymentProcessor paymentProcessor = paymentProcessorFactory.getPaymentProcessor(mode);
        if (paymentProcessor != null && paymentProcessor.process()) {
            return "{\"success\": true}";
        }
        return "{\"success\": false}";
    }
}
