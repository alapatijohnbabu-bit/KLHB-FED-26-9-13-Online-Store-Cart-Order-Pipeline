# Testing plan

## Automated smoke test
Compile all production Java sources into `trash`, compile `StoreSmokeTest.java` against them, and run `com.store.StoreSmokeTest`. It verifies a positive cart total, checkout confirmation, inventory decrement and order pipeline advancement.

## Manual UI checklist
- [ ] Launch app and confirm the four tabs are visible.
- [ ] Search by product name, category and ID.
- [ ] Add an available product to the cart.
- [ ] Reject zero, negative and non-numeric quantities.
- [ ] Reject quantities above current stock.
- [ ] Remove an item and verify total recalculates.
- [ ] Checkout with each simulated payment option.
- [ ] Confirm stock decreases after checkout.
- [ ] Advance an order from CONFIRMED to PACKED to SHIPPED to DELIVERED.
- [ ] Confirm a delivered order cannot be cancelled or advanced.
- [ ] Cancel an eligible order and verify stock is restored.
- [ ] Create a reservation for a future date; reject today's or past dates.
- [ ] Run the availability check and inspect any flagged reservation.
- [ ] Close and reopen the app; verify the documented in-memory reset.

## Current scope
These are project-level checks, not a claim of exhaustive production testing. Do not store real payment details or customer secrets in test data.
