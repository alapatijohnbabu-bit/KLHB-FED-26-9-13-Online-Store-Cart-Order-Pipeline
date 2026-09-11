# 🛒 Online Store Cart and Order Pipeline

A Java-based **Online Store Cart and Order Pipeline** that simulates the core operations of an e-commerce shopping system. The application allows users to browse products, add and manage items in a shopping cart, proceed to checkout, make a simulated payment, place orders, and track their order status.

## 📌 Project Overview

The project demonstrates how an online shopping system manages the complete journey of a customer order — from selecting a product to successful delivery.

The application is developed using **Java** and focuses on applying fundamental Object-Oriented Programming concepts and Java programming techniques in a practical project.

## 🎯 Objectives

* To develop a basic online shopping system using Java.
* To implement shopping cart functionality.
* To manage products and their availability.
* To calculate cart and order totals.
* To simulate the checkout and payment process.
* To implement an order processing pipeline.
* To track the status of placed orders.
* To demonstrate Object-Oriented Programming concepts in Java.

## ⚙️ Features

### 👤 Customer Management

* Store customer details.
* Create and manage customer orders.
* View order information.

### 📦 Product Management

* Display available products.
* Store product ID, name, category, price, and quantity.
* Check product availability.

### 🛒 Shopping Cart

* Add products to the cart.
* Remove products from the cart.
* Update product quantities.
* View cart items.
* Calculate the total cart value.

### 💳 Checkout & Payment

* Proceed to checkout.
* Calculate the final order amount.
* Simulate payment processing.
* Confirm successful orders.

### 🚚 Order Pipeline

Orders move through different stages:

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

## 🧩 Main Modules

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

## 🏗️ Project Structure

The project is organized into multiple classes based on their responsibilities.

```text
OnlineStore/
│
├── Customer.java
├── Product.java
├── Cart.java
├── CartItem.java
├── Order.java
├── OrderItem.java
├── Payment.java
├── Inventory.java
├── OrderStatus.java
└── Main.java
```

## 💻 Technologies Used

* **Java**
* **JDK 17+**
* **Object-Oriented Programming**
* **Java Collections Framework**
* **Exception Handling**
* **File Handling / Database** *(if implemented)*
* **Git**
* **GitHub**

## 🧠 Java Concepts Used

This project demonstrates the following Java concepts:

* Classes and Objects
* Encapsulation
* Inheritance
* Polymorphism
* Abstraction
* Constructors
* Methods
* Interfaces
* Arrays and ArrayList
* Conditional Statements
* Loops
* Exception Handling
* Enumerations
* Packages
* File Handling
* JDBC *(if database connectivity is implemented)*

## 🔄 Order Processing Flow

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
        /       \
      Yes        No
       │          │
       └────┐     ▼
            │   Checkout
            │      │
            │      ▼
            │    Payment
            │      │
            │      ▼
            │  Order Placed
            │      │
            │      ▼
            │  Order Confirmed
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
            └──────┴────► END
```

## 📋 Requirements

### Hardware

* Processor: Intel Core i3 or equivalent and above
* RAM: Minimum 4 GB
* Storage: Minimum 500 MB free space

### Software

* Windows / Linux / macOS
* JDK 17 or above
* IntelliJ IDEA / Eclipse / VS Code / NetBeans
* Git
* GitHub

## 🚀 How to Run

1. Install **JDK 17 or later**.
2. Clone this repository.
3. Open the project in your preferred Java IDE.
4. Compile the Java source files.
5. Run `Main.java`.
6. Follow the instructions displayed by the application.

## 📈 Future Enhancements

The project can be extended with:

* User registration and login
* Graphical User Interface (GUI)
* MySQL database integration
* Real-time inventory management
* Multiple payment methods
* Discount and coupon system
* Product search and filtering
* Admin dashboard
* Order history
* Email/SMS order notifications
* Online payment gateway integration

## 👨‍💻 Project Information

**Project No.:** 13
**Project Title:** Online Store Cart and Order Pipeline
**Programming Language:** Java
**Project Type:** Semester Project

## 📄 License

This project is developed for **educational purposes** as part of a semester project.
