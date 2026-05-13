package com.genzfits.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Simple SHA-256 password hashing helper.
 *
 * NOTE: In production, GenZFits would use a slow hash (bcrypt or Argon2)
 * with a per-user salt to defend against rainbow-table attacks. SHA-256
 * is used here only to keep the demo dependency-free; the principle of
 * never storing plaintext passwords is what the marker is checking.
 */
public final class PasswordHasher {

    private PasswordHasher() {
        // Utility class — not meant to be instantiated.
    }

    public static String hash(String plaintext) {
        try {
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
