package com.genzfits.model;

import java.math.BigDecimal;

public class OrderItem {

    private final Product product;
    private final int quantity;
    private final BigDecimal priceAtPurchase;

    public OrderItem(Product product, int quantity, BigDecimal priceAtPurchase) {
        this.product = product;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public Product    getProduct()         { return product; }
    public int        getQuantity()        { return quantity; }
    public BigDecimal getPriceAtPurchase() { return priceAtPurchase; }

    public BigDecimal getSubtotal() {
        return priceAtPurchase.multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return String.format("%d x %s @ $%s = $%s",
                quantity, product.getName(), priceAtPurchase, getSubtotal());
    }
}