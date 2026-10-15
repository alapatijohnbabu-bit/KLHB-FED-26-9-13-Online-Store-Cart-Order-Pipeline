package com.store.service;

import com.store.model.Product;
import java.util.*;

public class CatalogService {
    private final List<Product> products;
    public CatalogService(List<Product> products) { this.products = new ArrayList<>(products); }
    public List<Product> getAll() { return Collections.unmodifiableList(products); }
    public Optional<Product> findById(String id) { return products.stream().filter(p -> p.getId().equalsIgnoreCase(id)).findFirst(); }
    public List<Product> search(String query) {
        String q = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        return products.stream().filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(q)
                || p.getCategory().toLowerCase(Locale.ROOT).contains(q) || p.getId().toLowerCase(Locale.ROOT).contains(q)).toList();
    }
}