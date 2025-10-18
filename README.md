# -ProductInventory-SpringBoot
# 📦 Product Inventory Management System

A Spring Boot application for managing tech product inventory. This demo allows users to add, edit, delete, and search products with image upload and pricing logic. Built with Thymeleaf, Bootstrap 5, and RESTful architecture.

## 🎥 Demo Video

[![Watch the demo](https://img.youtube.com/vi/oEmrC52DHdg/0.jpg)](https://youtu.be/oEmrC52DHdg?si=P1kcTS2WQTN4FXEE)

## 🚀 Features

- Add, edit, delete products
- Upload and preview product images
- Calculate net price (Total - Discount + Tax)
- Search products by Id
- Responsive UI with gradient theme
- Audit info: Created By & Created At

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring MVC, Spring Data JPA
- Thymeleaf
- Bootstrap 5
- Maven
- Git & GitHub

## 📂 Folder Structure
ProductInventory-Springboot/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── chethanura/
│   │   │           └── inventory/
│   │   │               ├── controller/       # Handles HTTP requests
│   │   │               ├── entity/           # JPA entities (Product.java, etc.)
│   │   │               ├── repository/       # Spring Data JPA interfaces
│   │   │               ├── service/          # Business logic
│   │   │               └── InventoryApplication.java
│   │   └── resources/
│   │       ├── static/                      # CSS, JS, images
│   │       ├── templates/                   # Thymeleaf HTML files
│   │       │   ├── fragments/               # Reusable layout parts (navbar, footer)
│   │       │   ├── home.html
│   │       │   ├── product-list.html
│   │       │   ├── add-product.html
│   │       │   ├── search.html
│   │       │   ├── about.html
│   │       │   └── contact.html
│   │       └── application.properties       # App configuration
├── uploads/                                 # Uploaded product images
├── .gitignore                               # Git ignore rules
├── README.md                                # Project overview
├── pom.xml                                  # Maven dependencies


## 🧪 Local Setup

```bash
git clone https://github.com/ChethanURA/ProductInventory-Springboot.git
cd ProductInventory-Springboot
./mvnw spring-boot:run

Then open http://localhost:8080 in your browser.
