package com.genzfits.model;

public class Admin extends User {

    private final String adminLevel;

    public Admin(String id, String email, String passwordHash,
                 String fullName, String adminLevel) {
        super(id, email, passwordHash, fullName);
        this.adminLevel = adminLevel;
    }

    public String getAdminLevel() { return adminLevel; }

    @Override
    public String getRoleLabel() {
        return "Admin (" + adminLevel + ")";
    }
}