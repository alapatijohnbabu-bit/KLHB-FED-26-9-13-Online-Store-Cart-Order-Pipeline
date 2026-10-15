# Software Requirements — Online Store Cart & Order Pipeline

## Purpose
Plan a Java application that demonstrates a customer shopping workflow from product discovery to order tracking. The team will implement and verify the requirements below in stages.

## Functional requirements
1. Display products with ID, name, category, price in INR and available stock.
2. Search products by ID, name or category.
3. Add products to a cart after validating positive quantity and available stock.
4. Remove cart items and calculate line totals and cart total.
5. Validate stock before checkout.
6. Simulate a payment choice without collecting real financial information.
7. Create an order after successful simulated checkout and update stock.
8. Track orders through PLACED, CONFIRMED, PACKED, SHIPPED and DELIVERED.
9. Permit cancellation only while an order is eligible and restore stock when appropriate.
10. Record future-dated reservations.
11. Check reservation quantities against available stock and flag reservations needing customer action.
12. Suggest in-stock alternatives from the same category where available.

## Non-functional requirements
- Java 17 or later.
- A desktop interface may use Java Swing if selected by the team.
- Use object-oriented models and separate service logic from the user interface.
- Validate user input and display readable error messages.
- Keep compiled .class files out of Git; use the existing bin/ directory for local compilation output.
- Provide repeatable compile and test instructions.
- Keep meaningful commit history for each team member.

## Known scope and limitations
These are requirements, not a statement that the features have already been implemented. Data persistence, authentication, real payment integration, notifications and concurrency-safe stock holds are not assumed. Update this document as decisions are finalized and tests confirm implementation.