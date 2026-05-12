package com.genzfits.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final Customer owner;
    private final List<CartItem> items;

    public ShoppingCart(Customer owner) {
        this.owner = owner;
        this.items = new ArrayList<>();
    }

    public Customer       getOwner() { return owner; }
    public List<CartItem> getItems() { return items; }

    public void addItem(Product product, int quantity) {
        for (CartItem existing : items) {
            if (existing.getProduct().getId().equals(product.getId())) {
                existing.setQuantity(existing.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(String productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }
}