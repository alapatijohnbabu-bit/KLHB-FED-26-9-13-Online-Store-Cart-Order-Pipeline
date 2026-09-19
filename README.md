# 🛒 KLHB-FED-26-9-13-Online Store Cart & Order Pipeline

## Project Information

| Item | Details |
|---|---|
| **Project No.** | 13 |
| **Project Title** | Online Store Cart & Order Pipeline |
| **Branch / Code** | FED |
| **Academic Year Code** | 26 |
| **Team ID** | 9 |
| **Supervisor** | Rakesh.K |
| **Programming Language** | Java |
| **Current Phase** | Project setup and repository structuring |

## Team Members

| S. No. | Name | KLH ID | GitHub Username |
|---:|---|---|---|
| 1 | K. Vishnu Preetham | 2620090013 | kandurivishnupreetham-pixel |
| 2 | K. Shashank Goud | 2620030057 | Shashank763304 |
| 3 | A. John Babu | 2620040010 | alapatijohnbabu-bit |

## Abstract

The **Online Store Cart & Order Pipeline** is a Java-based e-commerce simulation that models the flow of a customer order from product selection through delivery. The project demonstrates fundamental Object-Oriented Programming concepts and core Java programming techniques through a practical online shopping workflow.

The system is designed to support product and inventory management, shopping-cart operations, checkout, simulated payment processing, order creation, and order-status tracking. The order pipeline represents the progression of an order through stages such as **Placed → Confirmed → Packed → Shipped → Delivered**, with cancellation handled where applicable.

## Objectives

- Develop a basic online shopping system using Java.
- Implement product and inventory management.
- Add, remove, update, and view cart items.
- Calculate cart and order totals.
- Simulate checkout and payment processing.
- Create and track customer orders.
- Model an order-processing pipeline.
- Apply Object-Oriented Programming principles in a practical project.

## Features

### Customer Management
- Store customer details.
- Create and manage customer orders.
- View order information.

### Product & Inventory Management
- Display available products.
- Store product ID, name, category, price, and quantity.
- Check product availability.

### Shopping Cart
- Add products to the cart.
- Remove products from the cart.
- Update product quantities.
- View cart items.
- Calculate total cart value.

### Checkout & Payment
- Proceed to checkout.
- Calculate the final order amount.
- Simulate payment processing.
- Confirm successful orders.

### Order Pipeline

```text
Order Placed
     ↓
Order Confirmed
     ↓
Order Packed
     ↓
Order Shipped
     ↓
Order Delivered
```

Orders can also be cancelled when applicable.

## System Modules

```text
Customer Module
      ↓
Product Module
      ↓
Cart Module
      ↓
Checkout Module
      ↓
Payment Module
      ↓
Order Module
      ↓
Order Tracking Module
```

## Repository Structure

```text
KLHB-FED-26-9-13-Online Store Cart & Order Pipeline/
│
├── src/                         # Java source code
├── docs/                        # Requirements, design and diagrams
├── data/                        # Sample/input data or data-source reference
├── results/                     # Outputs and test evidence
├── reports/                     # Phase and final report material
├── README.md
└── .gitignore
```

### Directory Usage

- **src/** — Java source code and application entry point.
- **docs/** — requirements, design documents, flowcharts and diagrams.
- **data/** — sample/input data or documented external data-source references.
- **results/** — verified program outputs, testing evidence and screenshots.
- **reports/** — phase deliverables and final report material.

## Technologies Used

- Java
- JDK 17+
- Object-Oriented Programming
- Java Collections Framework
- Exception Handling
- Git
- GitHub

Only technologies actually implemented in the source code should be described as implemented.

## Order Processing Flow

```text
              START
                │
                ▼
      View Available Products
                │
                ▼
         Select Product
                │
                ▼
          Add to Cart
                │
                ▼
         Modify Cart?
          /         \
        Yes          No
         │            │
         └─────┐      ▼
               │   Checkout
               │      │
               │      ▼
               │    Payment
               │      │
               │      ▼
               │  Order Placed
               │      │
               │      ▼
               │ Order Confirmed
               │      │
               │      ▼
               │    Packed
               │      │
               │      ▼
               │    Shipped
               │      │
               │      ▼
               │   Delivered
               │      │
               └──────┴──────► END
```

## Requirements

### Hardware
- Processor: Intel Core i3 equivalent or above
- RAM: Minimum 4 GB
- Storage: At least 500 MB free space

### Software
- Windows / Linux / macOS
- JDK 17 or later
- VS Code / IntelliJ IDEA / Eclipse / NetBeans
- Git
- GitHub account

## Setup and Execution

### 1. Install Java

Install **JDK 17 or later** and verify:

```bash
java -version
javac -version
```

### 2. Clone the Repository

```bash
git clone <repository-url>
cd <repository-folder>
```

### 3. Compile

For a simple source layout:

```bash
javac -d out src/*.java
```

### 4. Run

```bash
java -cp out Main
```

If the final source uses packages or a different build structure, these commands must be updated to match the implemented project.

## GitHub Submission & Contribution Rules

The repository follows the project submission norms provided by the course:

1. One repository is maintained for the team for the full project duration.
2. The repository follows the official project naming information provided by the supervisor.
3. Required top-level directories are **src, docs, data, results, and reports**.
4. Every team member must make meaningful commits using their own GitHub account.
5. Contributions must be made progressively throughout the project phases.
6. Maintain at least one meaningful team commit per week.
7. Tag phase deliverables, for example:
   - `review-1`
   - `review-2`
   - `final`
8. Grant repository access to the **Supervisor** and **Course Coordinator**.
9. Keep the repository accessible until final project evaluation is completed.

## Phase Status

**Current Phase:** Project setup and repository structuring.

Update this section whenever a new official project phase begins.

## Submission Checklist

- [ ] Required repository name confirmed and applied.
- [x] Project title recorded as provided by the supervisor.
- [x] Team member names and KLH IDs added.
- [x] Team GitHub usernames added.
- [x] Supervisor name added.
- [ ] Course Coordinator access granted.
- [ ] Supervisor access granted.
- [ ] src populated with actual Java source code.
- [ ] docs populated with approved documentation and diagrams.
- [ ] data populated with required sample data or a documented data source.
- [ ] results populated with verified outputs/test evidence.
- [ ] reports populated with phase deliverables.
- [ ] Weekly meaningful commits maintained.
- [ ] Each team member has meaningful commits from their own account.
- [ ] Phase tags created.
- [ ] README execution instructions tested against the actual source code.

## Future Enhancements

Potential extensions, if approved and implemented, include:

- User registration and login
- Graphical User Interface (GUI)
- Database integration
- Real-time inventory management
- Multiple payment methods
- Discount and coupon system
- Product search and filtering
- Admin dashboard
- Order history
- Notification system

## Academic Use

This repository is maintained as an educational semester project. Documentation should remain consistent with the actual implementation and submitted deliverables.

## License

This project is developed for educational purposes as part of a semester project.
