# Proposed Architecture

This is a planning diagram, not a claim that these classes currently exist in the repository.

```text
Application Entry Point
  └── Desktop UI (if Swing is used)
        └── Application/Data Wiring
              ├── Product Catalogue
              ├── Cart and Inventory
              ├── Checkout and Simulated Payment
              ├── Order Pipeline
              └── Reservations and Availability
```

## Suggested separation of responsibilities
- **model/** — product, customer, cart, order and reservation domain objects.
- **service/** — catalogue search, cart operations, stock checks, checkout, payment simulation and reservation rules.
- **data/** — sample data and data-source integration.
- **ui/** — desktop interface and user interaction, if a Swing UI is implemented.
- **test/** — repeatable checks for business rules.

## Data lifecycle
The repository includes sample catalogue data under `data/products.csv`. No source code is currently committed to load it, so do not assume that the CSV is integrated into a running application. The team must document whether data is held in memory or persisted once implementation is decided.