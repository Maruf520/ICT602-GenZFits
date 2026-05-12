package com.genzfits.model;

import java.math.BigDecimal;
import java.util.UUID;

public class AfterpayPayment extends PaymentMethod {

    private final String afterpayAccountEmail;

    public AfterpayPayment(String ownerName, String afterpayAccountEmail) {
        super(ownerName);
        this.afterpayAccountEmail = afterpayAccountEmail;
    }

    @Override
    public PaymentResult process(BigDecimal amount) {
        if (afterpayAccountEmail == null || !afterpayAccountEmail.contains("@")) {
            return new PaymentResult(false, null, "Invalid Afterpay account");
        }
        BigDecimal instalment = amount.divide(new BigDecimal(4), 2,
                java.math.RoundingMode.HALF_UP);
        String txnId = "AFTPY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return new PaymentResult(true, txnId,
                "Afterpay plan created: 4 x $" + instalment + " fortnightly");
    }

    @Override
    public String getDisplayLabel() {
        return "Afterpay (" + afterpayAccountEmail + ")";
    }
}