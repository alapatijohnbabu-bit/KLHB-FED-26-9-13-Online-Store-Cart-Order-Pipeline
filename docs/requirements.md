# Software Requirements — Online Store Cart and Order Pipeline

## Purpose
Build a Java Swing desktop demonstration of the customer shopping workflow from product discovery to order tracking, including future-dated reservations and stock availability alerts.

## Functional requirements
1. Display seeded products with ID, name, category, price in INR and available stock.
2. Search products by ID, name or category.
3. Add products to a cart after validating positive quantity and current stock.
4. Remove cart items and calculate line totals and cart total.
5. Validate stock before checkout.
6. Simulate a payment choice without collecting real financial information.
7. Create an order after successful simulated checkout and reduce stock.
8. Track orders through PLACED, CONFIRMED, PACKED, SHIPPED and DELIVERED.
9. Permit cancellation only while an order is eligible; restore stock when cancelled.
10. Record future-dated reservations.
11. Check reservation quantities against current demo stock and flag reservations needing customer action.
12. Suggest in-stock alternatives from the same category where available.

## Non-functional requirements
- Java 17 or later.
- Swing desktop UI, no external runtime libraries required.
- Use object-oriented models and separate service logic from UI.
- Validate user input and display readable error messages.
- Keep compiled class files out of Git.
- Provide repeatable compile and smoke-test instructions.

## Known limitations
Data is in memory and resets on exit. Reservations are not stock holds and are not automatically fulfilled. Payment is simulated. Authentication, database persistence, notifications, concurrent reservation locking and real payment integration are out of scope for this prototype.
