
 // Service Layer for product browsing and search.
 
 // Wraps the ProductRepository so that business rules (e.g. filtering
 // out-of-stock items by default) can be added without changing the
 // DAL contract.
 
package com.genzfits.service;

import com.genzfits.data.ProductRepository;
import com.genzfits.model.Category;
import com.genzfits.model.Product;

import java.util.List;
import java.util.Optional;


public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> browseByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }
}
