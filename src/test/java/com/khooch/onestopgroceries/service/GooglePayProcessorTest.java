package com.khooch.onestopgroceries.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GooglePayProcessorTest {

    @Test
    public void testProcess() {
        GooglePayProcessor processor = new GooglePayProcessor();
        boolean result = processor.process();
        assertTrue(result, "GooglePayProcessor should return true when processing a payment.");
    }
}
