package com.store.service;

import com.store.model.*;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {
    private final List<Reservation> reservations = new ArrayList<>();
    private final CatalogService catalog;
    private final InventoryService inventory;
    public ReservationService(CatalogService catalog, InventoryService inventory) { this.catalog = catalog; this.inventory = inventory; }
    public Reservation reserve(Product product, int quantity, LocalDate date) {
        if (product == null) throw new IllegalArgumentException("Select a product.");
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be positive.");
        if (date == null || !date.isAfter(LocalDate.now())) throw new IllegalArgumentException("Delivery date must be in the future.");
        Reservation reservation = new Reservation(product, quantity, date);
        reservations.add(reservation);
        return reservation;
    }
    public List<Reservation> getReservations() { return Collections.unmodifiableList(reservations); }
    public List<Product> alternatives(Product product) { return inventory.alternatives(catalog.getAll(), product); }
    public void cancel(String id) {
        Reservation r = reservations.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Reservation not found."));
        if (!r.getStatus().equals("SCHEDULED")) throw new IllegalStateException("Only scheduled reservations can be cancelled.");
        r.setStatus("CANCELLED");
    }
    public void checkAvailability() {
        for (Reservation r : reservations) if (r.getStatus().equals("SCHEDULED") && !inventory.isAvailable(r.getProduct(), r.getQuantity())) r.setStatus("ACTION REQUIRED");
    }
}