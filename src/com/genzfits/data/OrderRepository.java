package com.genzfits.data;

import com.genzfits.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//sData Access Layer (DAL) for Order records.
 

public class OrderRepository {

    private final Map<String, Order> ordersById = new HashMap<>();

    public void save(Order order) {
        ordersById.put(order.getId(), order);
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(ordersById.get(id));
    }

    /** All orders for one customer — supports order history screens. */
    public List<Order> findByCustomerId(String customerId) {
        List<Order> result = new ArrayList<>();
        for (Order o : ordersById.values()) {
            if (o.getCustomer().getId().equals(customerId)) {
                result.add(o);
            }
        }
        return result;
    }
}