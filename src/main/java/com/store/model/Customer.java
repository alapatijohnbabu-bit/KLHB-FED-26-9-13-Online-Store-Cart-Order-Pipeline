package com.store.model;

public class Customer {
    private final String id;
    private final String name;
    private final String email;

    public Customer(String id, String name, String email) {
        this.id = require(id, "Customer ID");
        this.name = require(name, "Name");
        this.email = require(email, "Email");
    }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " is required.");
        return value.trim();
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}