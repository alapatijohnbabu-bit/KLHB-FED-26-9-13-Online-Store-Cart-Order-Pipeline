package com.store.model;

import java.math.BigDecimal;

public class CartItem {
    private final Product product;
    private int quantity;
    public CartItem(Product product, int quantity) {
        if (product == null || quantity < 1) throw new IllegalArgumentException("Product and positive quantity are required.");
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least 1.");
        this.quantity = quantity;
    }
    public BigDecimal getLineTotal() { return product.getPrice().multiply(BigDecimal.valueOf(quantity)); }
    public CartItem copy() { return new CartItem(product, quantity); }
}