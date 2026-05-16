 // Service Layer for the shopping cart.
 
 // Coordinates business rules that span the cart and product stock
 // for example, refusing to add more units than are currently in stock.
 
 // The cart instance itself is owned by the Customer (composition); 
 // this service is stateless and works on whichever cart is passed in.

package com.genzfits.service;

import com.genzfits.model.Customer;
import com.genzfits.model.Product;
import com.genzfits.model.ShoppingCart;


public class CartService {

    
    // Add a product to a customer's cart, validating stock first.
          
    public void addToCart(Customer customer, Product product, int quantity) {
        // checking if quantity less then 1 to throw exception
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        // checking if quantity more then stock 
        if (quantity > product.getStockQuantity()) {
            // throws Exception if the requested quantity exceeds current stock for the product.
            throw new IllegalArgumentException(
                    "Only " + product.getStockQuantity()
                    + " unit(s) of " + product.getName() + " in stock.");
        }
        customer.getCart().addItem(product, quantity);
    }

    // remove Products from the cart
    public void removeFromCart(Customer customer, String productId) {
        customer.getCart().removeItem(productId);
    }

    // show cart item
    public ShoppingCart viewCart(Customer customer) {
        return customer.getCart();
    }
}
