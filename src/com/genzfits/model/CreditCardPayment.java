package com.genzfits.model;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditCardPayment extends PaymentMethod {

    private final String cardNumber;
    private final String expiryDate;

    public CreditCardPayment(String ownerName, String cardNumber, String expiryDate) {
        super(ownerName);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    @Override
    public PaymentResult process(BigDecimal amount) {
        if (cardNumber == null || cardNumber.length() < 13) {
            return new PaymentResult(false, null, "Invalid card number");
        }
        String txnId = "STRIPE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return new PaymentResult(true, txnId,
                "Credit card charged $" + amount + " successfully");
    }

    @Override
    public String getDisplayLabel() {
        String last4 = cardNumber.length() >= 4
                ? cardNumber.substring(cardNumber.length() - 4)
                : cardNumber;
        return "Credit Card ****" + last4;
    }
}