package com.genzfits;

import com.genzfits.data.OrderRepository;
import com.genzfits.data.ProductRepository;
import com.genzfits.data.UserRepository;
import com.genzfits.model.Category;
import com.genzfits.model.Customer;
import com.genzfits.model.Product;
import com.genzfits.presentation.ConsoleApp;
import com.genzfits.service.AuthService;
import com.genzfits.service.OrderService;
import com.genzfits.service.PaymentService;
import com.genzfits.service.ProductService;
import com.genzfits.util.PasswordHasher;

import java.math.BigDecimal;


    // This is the  entry point of this Application.
      //It contain other dependancy from other packages

public class Main {

    public static void main(String[] args) {


        UserRepository userRepository       = new UserRepository();
         ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository     = new OrderRepository();
        AuthService authService       = new AuthService(userRepository);
         ProductService productService = new ProductService(productRepository);
      PaymentService paymentService = new PaymentService();
        OrderService orderService     = new OrderService(orderRepository, paymentService);

  seedDemoData(userRepository, productRepository);


           ConsoleApp app = new ConsoleApp(authService, productService,  orderService);
        app.run();
    }

    private static void seedDemoData(UserRepository userRepository,
                                     ProductRepository productRepository) {
        Customer demo = new Customer(
                "C-DEMO0001",
                "maruf@genzfits.com",
                PasswordHasher.hash("maruf1234"),
                "Maruf");
        userRepository.save(demo);

        productRepository.save(new Product("P-001", "Slim-Fit Denim Jeans",    "UrbanLine",  Category.CLOTHING,  new BigDecimal("89.95"),  25, "S-001"));
        	productRepository.save(new Product("P-002", "Oversized Cotton Hoodie", "Streetwave", Category.CLOTHING,  new BigDecimal("69.00"),  40, "S-001"));
          productRepository.save(new Product("P-003", "Classic White Sneakers",  "PaceForm",   Category.FOOTWEAR,  new BigDecimal("129.50"), 15, "S-002"));
        productRepository.save(new Product("P-004", "Leather Crossbody Bag",   "Maven&Co",   Category.BAGS,      new BigDecimal("159.00"), 10, "S-002"));
         productRepository.save(new Product("P-005", "Gold Hoop Earrings",      "Lumière",    Category.JEWELLERY, new BigDecimal("45.00"),  50, "S-003"));
    }
}