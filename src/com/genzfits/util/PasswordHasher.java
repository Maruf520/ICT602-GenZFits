package com.genzfits.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


// Simple SHA-256 password hashing helper.
public final class PasswordHasher {

    
    public static String hash(String plaintext) {
        try {
            // declaring the algorithm to use
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(plaintext.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : bytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    public static boolean matches(String plaintext, String hashed) {
        return hash(plaintext).equals(hashed);
    }
}
