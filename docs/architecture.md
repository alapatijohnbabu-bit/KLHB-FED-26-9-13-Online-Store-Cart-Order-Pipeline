# Architecture

```text
Main
  └── StoreFrame (Swing UI)
        └── DataStore (demo wiring and seeded data)
              ├── CatalogService
              ├── CartService ── ShoppingCart ── CartItem
              ├── InventoryService ── Product
              ├── OrderService ── Order / OrderStatus
              │                  └── PaymentService (simulation)
              └── ReservationService ── Reservation
```

## Package map
- `com.store`: application entry point.
- `com.store.ui`: Swing windows, tables, buttons and user interaction.
- `com.store.model`: product, customer, cart, order and reservation domain objects.
- `com.store.service`: catalogue search, cart operations, stock checks, checkout, payments and reservations.
- `com.store.data`: sample products and service wiring.

## Data lifecycle
The current version stores objects in memory. Closing the app resets carts, orders and reservations. The CSV in `data/products.csv` is sample documentation data and is not loaded automatically by the current prototype.
