package com.khooch.onestopgroceries.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.khooch.onestopgroceries.service.BillingService;
import com.khooch.onestopgroceries.service.PaymentProcessor;
import com.khooch.onestopgroceries.service.PaymentProcessorFactory;

@Configuration
public class AppConfig {

    @Bean
    public PaymentProcessorFactory paymentProcessorFactory() {
        return new PaymentProcessorFactory();
    }

    @Bean
    public PaymentProcessor paymentProcessor(PaymentProcessorFactory factory) {
        return factory.getPaymentProcessor("gpay"); // or "credit_card"
    }

    @Bean
    public BillingService billingService(PaymentProcessor paymentProcessor) {
        return new BillingService(paymentProcessor);
    }
}
