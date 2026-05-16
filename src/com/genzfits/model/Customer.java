package com.genzfits.model;

import java.util.ArrayList;
import java.util.List;
//this class extends user class with some extra shoppiong behavour.
//The customer is the central actor for this platform.
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

    public ShoppingCart        getCart()                { return cart; } //The cart belongs to customer
    public List<Address>       getSavedAddresses()      { return savedAddresses; }
    public List<PaymentMethod> getSavedPaymentMethods() { return savedPaymentMethods; }

    
       // Add a new shipping address
    public void addAddress(Address address) {
        savedAddresses.add(address);
    }

    @Override
    public String getRoleLabel() {
        return "Customer";
    }
}