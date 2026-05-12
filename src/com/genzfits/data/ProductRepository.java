package com.genzfits.data;

import com.genzfits.model.Category;
import com.genzfits.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//Data Access Layer  for Product records.
 
public class ProductRepository {

    private final Map<String, Product> productsById = new HashMap<>();

    public void save(Product product) {
        productsById.put(product.getId(), product);
    }

    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productsById.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productsById.values());
    }

    /** Filter by category — supports the FR2 browsing requirement. */
    public List<Product> findByCategory(Category category) {
        List<Product> result = new ArrayList<>();
        for (Product p : productsById.values()) {
            if (p.getCategory() == category) {
                result.add(p);
            }
        }
        return result;
    }
}