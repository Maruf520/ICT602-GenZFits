package com.genzfits.service;

import com.genzfits.model.PaymentMethod;
import com.genzfits.model.PaymentResult;

import java.math.BigDecimal;

/**
 * Service Layer wrapper around payment processing (FR8).
 *
 * Delegates to the polymorphic PaymentMethod.process() so this class
 * does not know whether it is charging a credit card, an Afterpay
 * account, or any payment type added in the future. This is the
 * Open/Closed Principle in action: the service is closed against
 * modification but open to new payment-type extensions.
 */
public class PaymentService {

    public PaymentResult charge(PaymentMethod method, BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            return new PaymentResult(false, null, "Invalid payment amount");
        }
        if (method == null) {
            return new PaymentResult(false, null, "No payment method selected");
        }
        // Polymorphic call — the concrete subclass decides how to process.
        return method.process(amount);
    }
}
