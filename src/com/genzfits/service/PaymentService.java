package com.genzfits.service;

import com.genzfits.model.PaymentMethod;
import com.genzfits.model.PaymentResult;

import java.math.BigDecimal;


 // Service Layer wrapper around payment processing.
 
public class PaymentService {

    public PaymentResult charge(PaymentMethod method, BigDecimal amount) {
        // verifing if the amount is more than zero or not null
        if (amount == null || amount.signum() <= 0) {
            return new PaymentResult(false, null, "Invalid payment amount");
        }
        // verifing if payment method selected or not
        if (method == null) {
            return new PaymentResult(false, null, "No payment method selected");
        }
        return method.process(amount);
    }
}
