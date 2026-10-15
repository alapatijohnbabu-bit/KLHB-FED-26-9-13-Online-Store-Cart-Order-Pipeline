package com.store.service;

import com.store.model.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {
    private final InventoryService inventory;
    private final PaymentService payments;
    private final List<Order> orders = new ArrayList<>();
    private final AtomicInteger sequence = new AtomicInteger(1001);
    public OrderService(InventoryService inventory, PaymentService payments) { this.inventory = inventory; this.payments = payments; }
    public Order checkout(Customer customer, ShoppingCart cart, String paymentMethod) {
        if (cart.isEmpty()) throw new IllegalStateException("Your cart is empty.");
        List<CartItem> items = cart.getItems();
        for (CartItem item : items) if (!inventory.isAvailable(item.getProduct(), item.getQuantity()))
            throw new IllegalStateException("Stock changed for " + item.getProduct().getName() + ". Review your cart.");
        if (!payments.processPayment(cart.getTotal(), paymentMethod)) throw new IllegalStateException("Payment failed.");
        for (CartItem item : items) inventory.reserveStock(item.getProduct(), item.getQuantity());
        Order order = new Order("ORD-" + sequence.getAndIncrement(), customer, items, cart.getTotal());
        order.advance(); // confirmed after successful simulated payment
        orders.add(order);
        cart.clear();
        return order;
    }
    public List<Order> getOrders() { return Collections.unmodifiableList(orders); }
    public void advance(String orderId) {
        find(orderId).advance();
    }
    public void cancel(String orderId) {
        Order order = find(orderId);
        order.cancel();
        order.getItems().forEach(i -> inventory.releaseStock(i.getProduct(), i.getQuantity()));
    }
    private Order find(String id) { return orders.stream().filter(o -> o.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Order not found.")); }
}