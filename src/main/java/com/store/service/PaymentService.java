package com.store.service;

import java.math.BigDecimal;

/** Demo-only payment simulation. No real payment is collected or transmitted. */
public class PaymentService {
    public boolean processPayment(BigDecimal amount, String method) {
        if (amount == null || amount.signum() <= 0) throw new IllegalArgumentException("Payment amount must be positive.");
        if (method == null || method.isBlank()) throw new IllegalArgumentException("Select a payment method.");
        return true;
    }
}