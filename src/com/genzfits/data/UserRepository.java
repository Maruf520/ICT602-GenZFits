package com.genzfits.data;

import com.genzfits.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//Data Access Layer (DAL) for User records.

 //Architecture role: Data Access Layer. Called only by the Service Layer.

public class UserRepository {

    private final Map<String, User> usersByEmail = new HashMap<>();

    public void save(User user) {
        usersByEmail.put(user.getEmail().toLowerCase(), user);
    }

    public Optional<User> findByEmail(String email) {
        if (email == null) return Optional.empty();
        return Optional.ofNullable(usersByEmail.get(email.toLowerCase()));
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }
}