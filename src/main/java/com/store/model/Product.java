package com.store.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String id;
    private final String name;
    private final String category;
    private final BigDecimal price;
    private int stock;

    public Product(String id, String name, String category, BigDecimal price, int stock) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.category = Objects.requireNonNull(category);
        this.price = Objects.requireNonNull(price);
        if (price.signum() < 0 || stock < 0) throw new IllegalArgumentException("Price and stock cannot be negative.");
        this.stock = stock;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean hasStock(int quantity) { return quantity > 0 && stock >= quantity; }
    public void reduceStock(int quantity) {
        if (!hasStock(quantity)) throw new IllegalStateException("Insufficient stock for " + name);
        stock -= quantity;
    }
    public void restoreStock(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
        stock += quantity;
    }
    @Override public String toString() { return name + " (" + id + ")"; }
}