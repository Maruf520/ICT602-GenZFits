package com.genzfits.model;

import java.math.BigDecimal;

public abstract class PaymentMethod {

    protected final String ownerName;

    protected PaymentMethod(String ownerName) {
        this.ownerName = ownerName;
    }

    // getter method
    public String getOwnerName() { return ownerName; }

    // inititing payment procedure
    public abstract PaymentResult process(BigDecimal amount);

    // show results of the payment
    public abstract String getDisplayLabel();
}