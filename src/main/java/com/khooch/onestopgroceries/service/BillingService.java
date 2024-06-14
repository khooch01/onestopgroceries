package com.khooch.onestopgroceries.service;

public class BillingService {
    private PaymentProcessor paymentProcessor;

    public BillingService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public boolean completePayment() {
        return paymentProcessor.process();
    }
}
