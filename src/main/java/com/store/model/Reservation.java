package com.store.model;

import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    private final String id = "RSV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    private final Product product;
    private final int quantity;
    private final LocalDate deliveryDate;
    private String status = "SCHEDULED";
    public Reservation(Product product, int quantity, LocalDate deliveryDate) {
        if (product == null || quantity < 1 || deliveryDate == null || !deliveryDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Choose a product, positive quantity and a future delivery date.");
        this.product = product; this.quantity = quantity; this.deliveryDate = deliveryDate;
    }
    public String getId() { return id; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}