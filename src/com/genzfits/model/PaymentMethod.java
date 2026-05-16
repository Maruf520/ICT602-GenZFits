package com.genzfits.model;

import java.math.BigDecimal;

public abstract class PaymentMethod {

    protected final String ownerName;

    protected PaymentMethod(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() { return ownerName; }

    public abstract PaymentResult process(BigDecimal amount);

    public abstract String getDisplayLabel();
}