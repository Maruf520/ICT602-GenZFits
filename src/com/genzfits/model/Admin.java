package com.genzfits.model;


//This class extends the user class
//This is for administration role of this system.
public class Admin extends User {

    private final String adminLevel;

    //this is the level of admin role
    public Admin(String id, String email, String passwordHash,
                 String fullName, String adminLevel) {
        super(id, email, passwordHash, fullName);
        this.adminLevel = adminLevel;
    }

    public String getAdminLevel() { return adminLevel; }

    // assigning a user with admin priviledge
    @Override
    public String getRoleLabel() {
        return "Admin (" + adminLevel + ")";
    }
}