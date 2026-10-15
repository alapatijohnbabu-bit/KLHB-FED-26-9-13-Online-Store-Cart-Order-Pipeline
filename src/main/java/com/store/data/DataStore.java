package com.store.data;

import com.store.model.*;
import com.store.service.*;
import java.math.BigDecimal;
import java.util.List;

public class DataStore {
    private final Customer customer = new Customer("CUS-001", "Guest Customer", "guest@example.com");
    private final List<Product> products = List.of(
        new Product("P101", "Wireless Headphones", "Electronics", new BigDecimal("2499.00"), 12),
        new Product("P102", "Mechanical Keyboard", "Electronics", new BigDecimal("3299.00"), 8),
        new Product("P103", "USB-C Hub", "Electronics", new BigDecimal("1599.00"), 15),
        new Product("P104", "Cotton T-Shirt", "Clothing", new BigDecimal("599.00"), 25),
        new Product("P105", "Everyday Backpack", "Accessories", new BigDecimal("1299.00"), 10),
        new Product("P106", "Desk Lamp", "Home", new BigDecimal("899.00"), 14),
        new Product("P107", "Notebook Set", "Stationery", new BigDecimal("249.00"), 40),
        new Product("P108", "Bluetooth Speaker", "Electronics", new BigDecimal("1899.00"), 6),
        new Product("P109", "Travel Mug", "Home", new BigDecimal("399.00"), 18),
        new Product("P110", "Running Shoes", "Clothing", new BigDecimal("2799.00"), 9)
    );
    private final CatalogService catalog = new CatalogService(products);
    private final InventoryService inventory = new InventoryService();
    private final ShoppingCart cart = new ShoppingCart();
    private final CartService cartService = new CartService(cart);
    private final OrderService orderService = new OrderService(inventory, new PaymentService());
    private final ReservationService reservationService = new ReservationService(catalog, inventory);
    public Customer getCustomer() { return customer; }
    public CatalogService getCatalog() { return catalog; }
    public CartService getCartService() { return cartService; }
    public OrderService getOrderService() { return orderService; }
    public ReservationService getReservationService() { return reservationService; }
    public InventoryService getInventoryService() { return inventory; }
}