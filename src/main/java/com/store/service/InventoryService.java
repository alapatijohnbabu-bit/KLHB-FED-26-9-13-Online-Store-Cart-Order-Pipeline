package com.store.service;

import com.store.model.Product;
import java.util.List;

public class InventoryService {
    public boolean isAvailable(Product product, int quantity) { return product != null && product.hasStock(quantity); }
    public void reserveStock(Product product, int quantity) { product.reduceStock(quantity); }
    public void releaseStock(Product product, int quantity) { product.restoreStock(quantity); }
    public List<Product> alternatives(List<Product> products, Product requested) {
        return products.stream().filter(p -> !p.getId().equals(requested.getId()))
                .filter(p -> p.getCategory().equalsIgnoreCase(requested.getCategory()) && p.getStock() > 0)
                .toList();
    }
}