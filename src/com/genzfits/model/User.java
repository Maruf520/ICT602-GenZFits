package com.genzfits.model;

//This is the abstract base class for all users in this system.
public abstract class User {

    protected final String id;
    protected final String email;
    protected final String passwordHash; //here we do not store plane text. we make the hash of password.
    protected final String fullName;

    protected User(String id, String email, String passwordHash, String fullName) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
    }

    public String getId()           { return id; }
    public String getEmail()        { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName()     { return fullName; }

    public abstract String getRoleLabel();
}