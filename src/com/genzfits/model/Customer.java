package com.genzfits.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private final ShoppingCart cart;
    private final List<Address> savedAddresses;
    private final List<PaymentMethod> savedPaymentMethods;

    public Customer(String id, String email, String passwordHash, String fullName) {
        super(id, email, passwordHash, fullName);
        this.cart = new ShoppingCart(this);
        this.savedAddresses = new ArrayList<>();
        this.savedPaymentMethods = new ArrayList<>();
    }

    public ShoppingCart        getCart()                { return cart; }
    public List<Address>       getSavedAddresses()      { return savedAddresses; }
    public List<PaymentMethod> getSavedPaymentMethods() { return savedPaymentMethods; }

    public void addAddress(Address address) {
        savedAddresses.add(address);
    }

    public void addPaymentMethod(PaymentMethod method) {
        savedPaymentMethods.add(method);
    }

    @Override
    public String getRoleLabel() {
        return "Customer";
    }
}