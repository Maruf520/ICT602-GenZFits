package com.genzfits.presentation;

import com.genzfits.model.AfterpayPayment;
import com.genzfits.model.Address;
import com.genzfits.model.CreditCardPayment;
import com.genzfits.model.Customer;
import com.genzfits.model.Order;
import com.genzfits.model.PaymentMethod;
import com.genzfits.model.Product;
import com.genzfits.model.User;
import com.genzfits.service.AuthService;
import com.genzfits.service.OrderService;
import com.genzfits.service.ProductService;

import java.util.List;
import java.util.Optional;


//It shows menus and collect inputs via Console ui and give output. 
//It is in the presentation layer.
public class ConsoleApp {

    private final AuthService    authService;
    	private final ProductService productService;
     private final OrderService   orderService;
     private final ConsoleUI      ui = new ConsoleUI();
     private final CartService cartService;

    private Customer loggedInCustomer = null;

    public ConsoleApp(AuthService authService, ProductService productService,
                       CartService cartService,OrderService orderService) {
         this.authService    = authService;
          this.productService = productService;
          this.cartService = cartService;
        this.orderService   = orderService;
    }

    public void run() {
        printWelcome();
        boolean running = true;

        while (running) {
            if (loggedInCustomer == null) {
                running = showAuthMenu();}
             else {
                running = showCustomerMenu();
            }}}

    private void printWelcome() {
        ui.println(" ");
        ui.println("  Welcome to GenZFits Fashion E-commerce");
        ui.println(" ");
    }

//Authentication
    private boolean showAuthMenu() {
         ui.heading("Login Menu");
        ui.println("1. Login");
         ui.println("2. Register new account");
        ui.println("3. Exit");

        int choice = ui.promptInt("Choose");
        switch (choice) {
           case 1:  doLogin();    return true;
             case 2:  doRegister(); return true;
            case 3:  return false;
          default: ui.println("Invalid"); return true;}}

	    private void doLogin() {
	        ui.heading("Customer Login");
	        String email    = ui.prompt("email");
	        String password = ui.prompt("passwor");
	        Optional<User> result = authService.login(email, password);
	        if (result.isEmpty()) {
	            ui.println("Login failed: incorrect email or password.");
	            return;
	        }
	       User user = result.get();
	        if (!(user instanceof Customer)) {
	            ui.println("This  only for customer login.");
	            return;
	        }
	
	        loggedInCustomer = (Customer) user;
	        ui.println("Logged in as " + loggedInCustomer.getFullName()
	                + " (" + loggedInCustomer.getRoleLabel() + ")");}

    private void doRegister() {
        ui.heading("Register New Customer");
        String name     = ui.prompt("Full name");
        String email    = ui.prompt("Email");
        String password = ui.prompt("Password");

        try {
            Customer created = authService.registerCustomer(email, password, name);
            ui.println("Account created. login as" + created.getEmail());
        } catch (IllegalArgumentException e) {
            ui.println("Registration failed: " + e.getMessage());
        }}
    private boolean showCustomerMenu() {
        ui.heading("Main Menu — " + loggedInCustomer.getFullName());
        	ui.println("1. Browse products");
        ui.println("2. View cart");
         ui.println("3. Checkout");
         	ui.println("4. View order history");
         		ui.println("5. Logout");
        ui.println("6. Exit");

        int choice = ui.promptInt("Choose");
        switch (choice) {
            case 1:  browseProducts();   return true;
            case 2: viewCart();         return true;
            case 3:  checkout();         return true;
	            case 4:  viewOrderHistory(); return true;
	            case 5:  logout();           return true;
            case 6:  return false;
            default: ui.println("Invalid"); return true;
        }
    }
    private void browseProducts() {
        ui.heading("Product Catalogue");

        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            ui.println("No products found.");
            return;
        }

        for (Product p : products) {
            ui.println("  " + p);
        }

        ui.blank();
        String addChoice = ui.prompt("Add to cart? (y/n)");
        if (!addChoice.equalsIgnoreCase("y")) return;

        String productId = ui.prompt("Product ID");
        Optional<Product> selected = productService.getProductById(productId);
        if (selected.isEmpty()) {
            ui.println("No product  Id with that ID.");
            return;
        }

        int qty = ui.promptInt("Quantity");
        try {
            cartService.addToCart(loggedInCustomer, selected.get(), qty);
            ui.println("Added " + qty + " x " + selected.get().getName() + " to cart.");
        } catch (IllegalArgumentException e) {
            ui.println("" + e.getMessage());
        }
    }


    private void viewCart() {
        ui.heading("Your Cart");
        var cart = cartService.viewCart(loggedInCustomer);
        if (cart.isEmpty()) {
            ui.println("Cart is empty.");
            return;
        }
        cart.getItems().forEach(item -> ui.println("  " + item));
        ui.println("  -----------------------------");
        ui.println("  Total: $" + cart.getTotal());
    }


     private void checkout() {
        ui.heading("Checkout");
        if (cartService.viewCart(loggedInCustomer).isEmpty()) {
            ui.println("Cart is empty - nothing to checkout.");
            return;
        }
        // Show what is being purchased.
        viewCart();

        // Collect shipping address.
        ui.blank();
        ui.println("Enter shipping address:");
        Address address = new Address(
                ui.prompt("Street"),
                ui.prompt("Suburb"),
                ui.prompt("State"),
                ui.prompt("Postcode"),
                "Australia");

        // Choose payment method.
        ui.blank();
        ui.println("Payment method:");
        ui.println("  1. Credit card");
        ui.println("  2. Afterpay");
        int payChoice = ui.promptInt("Choose");
        PaymentMethod method;
        if (payChoice == 1) {
            String number = ui.prompt("Card number");
            String expiry = ui.prompt("Expiry (MM/YY)");
            method = new CreditCardPayment(loggedInCustomer.getFullName(), number, expiry);
        } else if (payChoice == 2) {
            String afterpayEmail = ui.prompt("Afterpay account email");
            method = new AfterpayPayment(loggedInCustomer.getFullName(), afterpayEmail);
        } else {
            ui.println("Invalid payment method. Aborting checkout.");
            return;
        }

        // Place the order via the Service Layer.
        try {
            Order order = orderService.placeOrder(loggedInCustomer, address, method);
            ui.blank();
            ui.println("[OK] Order placed successfully!");
            ui.println("  Order ID:        " + order.getId());
            ui.println("  Status:          " + order.getStatus());
            ui.println("  Total charged:   $" + order.getTotalAmount());
            ui.println("  Payment:         " + method.getDisplayLabel());
            ui.println("  Transaction ID:  " + order.getPaymentTransactionId());
            ui.println("  Ships to:        " + order.getShippingAddress());
        } catch (Exception e) {
            ui.println("Checkout failed: " + e.getMessage());
        }
    }

    private void viewOrderHistory() {
        ui.heading("Order History");

        List<Order> history = orderService.getOrderHistory(loggedInCustomer);
        if (history.isEmpty()) {
            ui.println("No orders yet.");
            return;}

        for (Order o : history) {
         ui.println("  " + o.getId()
                    +" — " + o.getStatus()
                     +"— $" + o.getTotalAmount()
           + " — " + o.getPlacedAt().toLocalDate());
  }}

    private void logout() {
        ui.println("Logged out.., " + loggedInCustomer.getFullName() + ".");
        loggedInCustomer = null;
 }}