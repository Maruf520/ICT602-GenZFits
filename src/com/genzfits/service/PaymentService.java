package com.genzfits.service;

import com.genzfits.model.PaymentMethod;
import com.genzfits.model.PaymentResult;

import java.math.BigDecimal;


 // Service Layer wrapper around payment processing.
 
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
