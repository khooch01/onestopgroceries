package com.khooch.onestopgroceries.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BillingServiceTest {

    @Mock
    private PaymentProcessor paymentProcessor;

    @InjectMocks
    private BillingService billingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCompletePayment_Success() {
        when(paymentProcessor.process()).thenReturn(true);

        boolean result = billingService.completePayment();

        assertTrue(result, "Payment should be completed successfully.");
        verify(paymentProcessor, times(1)).process();
    }

    @Test
    public void testCompletePayment_Failure() {
        when(paymentProcessor.process()).thenReturn(false);

        boolean result = billingService.completePayment();

        assertFalse(result, "Payment should fail.");
        verify(paymentProcessor, times(1)).process();
    }
}
