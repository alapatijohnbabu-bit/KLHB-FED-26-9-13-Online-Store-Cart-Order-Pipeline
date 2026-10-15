package com.store.model;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingCart {
    private final Map<String, CartItem> items = new LinkedHashMap<>();
    public void add(Product product, int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be positive.");
        CartItem existing = items.get(product.getId());
        int newQuantity = quantity + (existing == null ? 0 : existing.getQuantity());
        if (!product.hasStock(newQuantity)) throw new IllegalStateException("Only " + product.getStock() + " unit(s) of " + product.getName() + " are available.");
        if (existing == null) items.put(product.getId(), new CartItem(product, quantity));
        else existing.setQuantity(newQuantity);
    }
    public void remove(String productId) { items.remove(productId); }
    public void update(String productId, int quantity) {
        CartItem item = items.get(productId);
        if (item == null) throw new IllegalArgumentException("Item is not in the cart.");
        if (!item.getProduct().hasStock(quantity)) throw new IllegalStateException("Requested quantity is unavailable.");
        item.setQuantity(quantity);
    }
    public List<CartItem> getItems() { return items.values().stream().map(CartItem::copy).toList(); }
    public BigDecimal getTotal() { return items.values().stream().map(CartItem::getLineTotal).reduce(BigDecimal.ZERO, BigDecimal::add); }
    public boolean isEmpty() { return items.isEmpty(); }
    public void clear() { items.clear(); }
}