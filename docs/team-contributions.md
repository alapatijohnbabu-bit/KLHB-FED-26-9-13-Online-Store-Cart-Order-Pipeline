# Suggested team contribution split

The following is a suggested allocation for three contributors. Adjust it to match the team's actual work. Each person should commit using their own GitHub account; do not share credentials or claim work completed by another member.

## Contributor A — product catalogue
- `Product.java`
- `Customer.java`
- `CatalogService.java`
- `DataStore.java`

## Contributor B — cart and inventory
- `CartItem.java`
- `ShoppingCart.java`
- `CartService.java`
- `InventoryService.java`
- `PaymentService.java`

## Contributor C — orders and reservations
- `Order.java`
- `OrderStatus.java`
- `OrderService.java`
- `Reservation.java`
- `ReservationService.java`

## Integration tasks
- `Main.java`, `StoreFrame.java` and `StoreSmokeTest.java) are shared integration files. One person can coordinate integration, but all contributors should review the overall application.
- Make small, meaningful commits, e.g. `Add stock validation to cart` or `Document reservation availability checks`.
- Pull the latest `main` before starting. For team safety, contributors should use branches or coordinate edits to avoid overwriting each other's work.
