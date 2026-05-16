package com.genzfits.service;

import com.genzfits.data.OrderRepository;
import com.genzfits.model.Address;
import com.genzfits.model.CartItem;
import com.genzfits.model.Customer;
import com.genzfits.model.Order;
import com.genzfits.model.OrderItem;
import com.genzfits.model.PaymentMethod;
import com.genzfits.model.PaymentResult;
import com.genzfits.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Order placement service — the core of the complex module being demonstrated.
 *
 * Orchestrates the full checkout flow:
 *   1. Validate that the cart is not empty.
 *   2. Build OrderItem snapshots from the current cart (freezes prices).
 *   3. Charge the customer via PaymentService.
 *   4. On payment success: deduct stock, save the order, mark it PAID,
 *      and clear the cart.
 *   5. On payment failure: leave stock and cart untouched.
 *
 * Architecture role: Service Layer — calls the Data Access Layer
 * (OrderRepository) and another Service (PaymentService). Knows nothing
 * about the CLI presentation.
 */
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository,
                        PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    /**
     * Place an order using everything currently in the customer's cart.
     *
     * @return the placed Order if payment succeeded.
     * @throws IllegalStateException    if the cart is empty.
     * @throws RuntimeException         if payment fails (caller decides
     *                                  how to surface the error to the user).
     */
    public Order placeOrder(Customer customer, Address shippingAddress,
                            PaymentMethod paymentMethod) {

        ShoppingCart cart = customer.getCart();
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty — nothing to order.");
        }

        // 1. Snapshot cart contents into immutable order items.
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            orderItems.add(new OrderItem(
                    cartItem.getProduct(),
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice()));   // freeze the price
        }

        // 2. Build the pending order.
        String orderId = "O-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Order order = new Order(orderId, customer, orderItems,
                shippingAddress, paymentMethod, cart.getTotal());

        // 3. Charge the payment method (polymorphic — see PaymentService).
        PaymentResult result = paymentService.charge(paymentMethod, order.getTotalAmount());
        if (!result.isSuccess()) {
            throw new RuntimeException("Payment failed: " + result.getMessage());
        }

        // 4. Payment OK — finalise the order.
        order.markPaid(result.getTransactionId());
        for (CartItem cartItem : cart.getItems()) {
            cartItem.getProduct().decreaseStock(cartItem.getQuantity());
        }
        orderRepository.save(order);
        cart.clear();

        return order;
    }

    public List<Order> getOrderHistory(Customer customer) {
        return orderRepository.findByCustomerId(customer.getId());
    }
}
