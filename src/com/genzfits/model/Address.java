package com.genzfits.model;

public class Address {

    private final String street;
    private final String suburb;
    private final String state;
    private final String postcode;
    private final String country;

    public Address(String street, String suburb, String state,
                   String postcode, String country) {
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
    }

    // getter methods
    public String getStreet()   { return street; }
    public String getSuburb()   { return suburb; }
    public String getState()    { return state; }
    public String getPostcode() { return postcode; }
    public String getCountry()  { return country; }

    // getting the whole address in a string form
    @Override
    public String toString() {
        return street + ", " + suburb + " " + state + " " + postcode + ", " + country;
    }
}