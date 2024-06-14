package com.khooch.onestopgroceries.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentProcessorFactoryTest {

    private PaymentProcessorFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new PaymentProcessorFactory();
    }

    @Test
    public void testGetPaymentProcessor_GPay() {
        PaymentProcessor processor = factory.getPaymentProcessor("gpay");
        assertTrue(processor instanceof GooglePayProcessor, "Factory should return GooglePayProcessor for 'gpay' mode.");
    }

    @Test
    public void testGetPaymentProcessor_CreditCard() {
        PaymentProcessor processor = factory.getPaymentProcessor("credit_card");
        assertTrue(processor instanceof CreditCardProcessor, "Factory should return CreditCardProcessor for 'credit_card' mode.");
    }

    @Test
    public void testGetPaymentProcessor_InvalidMode() {
        PaymentProcessor processor = factory.getPaymentProcessor("invalid");
        assertNull(processor, "Factory should return null for an invalid mode.");
    }
}
