package com.store;

import com.store.data.DataStore;
import com.store.model.Order;
import com.store.model.Product;

/** Dependency-free smoke test. Run with: java -cp out com.store.StoreSmokeTest */
public class StoreSmokeTest {
    public static void main(String[] args) {
        DataStore store = new DataStore();
        Product headphones = store.getCatalog().findById("P101").orElseThrow();
        int stockBefore = headphones.getStock();
        store.getCartService().add(headphones, 2);
        if (store.getCartService().total().signum() <= 0) throw new AssertionError("Cart total should be positive.");
        Order order = store.getOrderService().checkout(store.getCustomer(), store.getCartService().getCart(), "Demo Card");
        if (!order.getStatus().name().equals("CONFIRMED")) throw new AssertionError("Order should be confirmed after checkout.");
        if (headphones.getStock() != stockBefore - 2) throw new AssertionError("Stock should reduce after checkout.");
        store.getOrderService().advance(order.getId());
        if (!order.getStatus().name().equals("PACKED")) throw new AssertionError("Pipeline should advance from confirmed to packed.");
        System.out.println("PASS: catalog, cart total, checkout, inventory and order pipeline.");
    }
}