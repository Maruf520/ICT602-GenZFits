package com.genzfits.model;

import java.math.BigDecimal;

public class Product {

    private final String id;
    private final String name;
    private final String brand;
    private final Category category;
    private final BigDecimal price;
    private final String sellerId;
    private int stockQuantity;

    public Product(String id, String name, String brand, Category category,
                   BigDecimal price, int stockQuantity, String sellerId) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.sellerId = sellerId;
    }

    public String     getId()            { return id; }
    public String     getName()          { return name; }
    public String     getBrand()         { return brand; }
    public Category   getCategory()      { return category; }
    public BigDecimal getPrice()         { return price; }
    public int        getStockQuantity() { return stockQuantity; }
    public String     getSellerId()      { return sellerId; }

    public void decreaseStock(int quantity) {
        if (quantity > stockQuantity) {
            throw new IllegalArgumentException("Not enough stock for " + name);
        }
        this.stockQuantity -= quantity;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s — $%s (%d in stock)",
                id, name, brand, price, stockQuantity);
    }
}