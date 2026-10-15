package com.store.model;

public enum OrderStatus {
    PLACED, CONFIRMED, PACKED, SHIPPED, DELIVERED, CANCELLED;
    public OrderStatus next() {
        return switch (this) {
            case PLACED -> CONFIRMED;
            case CONFIRMED -> PACKED;
            case PACKED -> SHIPPED;
            case SHIPPED -> DELIVERED;
            case DELIVERED, CANCELLED -> this;
        };
    }
    public boolean canCancel() { return this == PLACED || this == CONFIRMED; }
}