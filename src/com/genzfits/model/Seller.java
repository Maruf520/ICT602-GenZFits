package com.genzfits.model;

import java.util.ArrayList;
import java.util.List;

//This class Extends User class with some seller specific behaviour. 

public class Seller extends User {

    private final String businessName; // this businessname is displayed in the product list.
    private final List<Product> products;

    public Seller(String id, String email, String passwordHash,
                  String fullName, String businessName) {
        super(id, email, passwordHash, fullName);
        this.businessName = businessName;
        this.products = new ArrayList<>();
    }

    public String        getBusinessName() { return businessName; }
    public List<Product> getProducts()     { return products; }

    
 // Register a new product under this seller
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String getRoleLabel() {
        return "Seller (" + businessName + ")";
    }
}