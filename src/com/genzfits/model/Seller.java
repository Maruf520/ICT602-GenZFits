package com.genzfits.model;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {

    private final String businessName;
    private final List<Product> products;

    public Seller(String id, String email, String passwordHash,
                  String fullName, String businessName) {
        super(id, email, passwordHash, fullName);
        this.businessName = businessName;
        this.products = new ArrayList<>();
    }

    public String        getBusinessName() { return businessName; }
    public List<Product> getProducts()     { return products; }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String getRoleLabel() {
        return "Seller (" + businessName + ")";
    }
}