# CartFlow — Java Swing Online Store Cart & Order Pipeline

A Java 17+ desktop demonstration app for the existing semester project. It includes product search, a shopping cart, stock validation, simulated checktrash, an order status pipeline, and future-dated reservations with stock alerts and category alternatives.

## Run (JDK 17+)

From the repository root:

```bash
javac -d trash $(find src/main/java -name "*.java")
java -cp trash com.store.Main
```

Windows PowerShell alternative:

```powershell
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d trash $files
java -cp trash com.store.Main
```

## Run the smoke test

```bash
javac -cp trash -d trash src/test/java/com/store/StoreSmokeTest.java
java -cp trash com.store.StoreSmokeTest
```

## Features

- Searchable seeded product catalog with category and stock.
- Add/remove cart items, quantity validation and INR totals.
- Checktrash validates inventory, simulates a payment method and creates an order.
- Order pipeline: PLACED → CONFIRMED → PACKED → SHIPPED → DELIVERED; eligible orders can be cancelled and stock restored.
- Future-dated product reservations.
- Reservation availability checks and same-category alternative suggestions when stock is insufficient.
- Java Swing tabbed interface with catalog, cart, orders and reservations.

## Important scope notes

This is a local educational prototype. Data is held in memory and resets when the application closes. Payment methods are simulations; no real money, bank details or payment provider are involved. Reservations are recorded for a future date but do not guarantee inventory or automatically fulfill themselves. The availability check flags reservations that need customer action and suggests in-stock alternatives; a real production service would require persistent storage, concurrency-safe stock holds, authentication and notification delivery.

## Project structure

- `src/main/java/com/store/Main.java` — entry point
- `model/` — product, customer, cart, order and reservation models
- `service/` — catalog, cart, inventory, payment, order and reservation logic
- `data/DataStore.java` — demo products and service wiring
- `ui/StoreFrame.java` — Swing user interface
- `src/test/java/com/store/StoreSmokeTest.java` — dependency-free smoke test
