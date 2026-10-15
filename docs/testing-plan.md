# Testing Plan

The Java source files and test classes have not yet been added to this repository. The checklist below describes planned verification; it is not evidence that tests have passed.

## Planned business-rule checks
- [ ] Application launches successfully on JDK 17 or later.
- [ ] Product catalogue displays the expected sample data.
- [ ] Search works by product name, category and ID.
- [ ] Valid cart quantities are accepted.
- [ ] Zero, negative, non-numeric and over-stock quantities are rejected.
- [ ] Removing or updating cart items recalculates totals correctly.
- [ ] Checkout validates stock before processing.
- [ ] Simulated payment creates an order only on the expected success path.
- [ ] Stock changes correctly after checkout.
- [ ] Order status transitions follow the agreed pipeline.
- [ ] Cancellation is restricted to eligible orders.
- [ ] Reservation date and quantity validation work as specified.
- [ ] Availability checks flag reservations that need action.
- [ ] Errors are displayed clearly and do not crash the application unexpectedly.

## Evidence to record
For each test, record the date, JDK version, steps, expected result and actual result. Save genuine screenshots or console logs under `results/`. Do not label sample or expected output as a passed test.

## Local compilation
Once Java files exist, compile into the existing `bin/` directory. Never commit generated `.class` files.