package com.store.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final String id;
    private final Customer customer;
    private final List<CartItem> items;
    private final BigDecimal total;
    private final LocalDateTime createdAt;
    private OrderStatus status;
    public Order(String id, Customer customer, List<CartItem> items, BigDecimal total) {
        this.id = id; this.customer = customer; this.items = items.stream().map(CartItem::copy).toList();
        this.total = total; this.createdAt = LocalDateTime.now(); this.status = OrderStatus.PLACED;
    }
    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<CartItem> getItems() { return items; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public OrderStatus getStatus() { return status; }
    public void advance() {
        if (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) throw new IllegalStateException("This order is already closed.");
        status = status.next();
    }
    public void cancel() {
        if (!status.canCancel()) throw new IllegalStateException("Only placed or confirmed orders can be cancelled.");
        status = OrderStatus.CANCELLED;
    }
}