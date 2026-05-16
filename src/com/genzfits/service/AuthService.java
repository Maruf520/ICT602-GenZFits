
//  Authentication and registration service.

// Architecture role: Service Layer (Business Logic).
//   works with the Data Access Layer (UserRepository).
//   works the Presentation Layer (LoginScreen / ConsoleApp).


package com.genzfits.service;

import com.genzfits.data.UserRepository;
import com.genzfits.model.Customer;
import com.genzfits.model.User;
import com.genzfits.util.PasswordHasher;

import java.util.Optional;
import java.util.UUID;


 // Primary Tasks:
 //    Register new customers.
 //    Verify credentials and return the authenticated user.
 //    Hashing passwords before store.

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
     // Register a new customer. Throws if the email is already taken.
     // Returns the newly created Customer (already persisted).
    
    public Customer registerCustomer(String email, String plaintextPassword,
                                     String fullName) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "An account with this email already exists.");
        }
        String id = "C-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String hashed = PasswordHasher.hash(plaintextPassword);
        Customer customer = new Customer(id, email, hashed, fullName);
        userRepository.save(customer);
        return customer;
    }

    
    // Verify the email/password combination.

     // @return the authenticated User on success, or empty on failure.
     //         The Optional return type forces the caller to handle the
     //         "wrong credentials" case explicitly fail-safe by design.
     
    public Optional<User> login(String email, String plaintextPassword) {
        Optional<User> found = userRepository.findByEmail(email);
        // verifing is user exist or not
        if (found.isEmpty()) {
            return Optional.empty();
        }
        User user = found.get();
        // matching password hash
        if (PasswordHasher.matches(plaintextPassword, user.getPasswordHash())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
