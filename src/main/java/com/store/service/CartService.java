package com.store.service;

import com.store.model.*;
import java.math.BigDecimal;

public class CartService {
    private final ShoppingCart cart;
    public CartService(ShoppingCart cart) { this.cart = cart; }
    public void add(Product product, int quantity) { cart.add(product, quantity); }
    public void remove(String productId) { cart.remove(productId); }
    public void update(String productId, int quantity) { cart.update(productId, quantity); }
    public ShoppingCart getCart() { return cart; }
    public BigDecimal total() { return cart.getTotal(); }
}