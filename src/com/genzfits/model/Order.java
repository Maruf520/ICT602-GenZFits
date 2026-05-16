package com.genzfits.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final String id;
    private final Customer customer;
    private final List<OrderItem> items;
    private final Address shippingAddress;
    private final PaymentMethod paymentMethod;
    private final BigDecimal totalAmount;
    private final LocalDateTime placedAt;
    private OrderStatus status;
    private String paymentTransactionId;

    public Order(String id, Customer customer, List<OrderItem> items,
                 Address shippingAddress, PaymentMethod paymentMethod,
                 BigDecimal totalAmount) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>(items);
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.placedAt = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public String          getId()                   { return id; }
    public Customer        getCustomer()             { return customer; }
    public List<OrderItem> getItems()                { return items; }
    public Address         getShippingAddress()      { return shippingAddress; }
    public PaymentMethod   getPaymentMethod()        { return paymentMethod; }
    public BigDecimal      getTotalAmount()          { return totalAmount; }
    public LocalDateTime   getPlacedAt()             { return placedAt; }
    public OrderStatus     getStatus()               { return status; }
    public String          getPaymentTransactionId() { return paymentTransactionId; }

    public void markPaid(String transactionId) {
        this.paymentTransactionId = transactionId;
        this.status = OrderStatus.PAID;
    }

    public void markShipped()   { this.status = OrderStatus.SHIPPED; }
    public void markDelivered() { this.status = OrderStatus.DELIVERED; }
    public void cancel()        { this.status = OrderStatus.CANCELLED; }
}