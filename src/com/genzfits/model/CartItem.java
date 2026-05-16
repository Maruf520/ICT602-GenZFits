package com.genzfits.model;

import java.math.BigDecimal;

public class CartItem {

    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // getter method
    public Product getProduct()  { return product; }
    public int     getQuantity() { return quantity; }

    // verifing quantity
    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        this.quantity = quantity;
    }

    // getting total amount of all the cart items
    public BigDecimal getSubtotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return String.format("%d x %s @ $%s = $%s",
                quantity, product.getName(), product.getPrice(), getSubtotal());
    }
}