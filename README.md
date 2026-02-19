🔐 Secure Employee Management System

A Spring Boot REST API for managing employee records with JWT authentication, role-based access control, and secure CRUD operations.

📌 Overview

This project is a secure backend system for employee management. It allows administrators and users to perform different operations on employee records while ensuring security with JWT and Spring Security.

✨ Features

🔑 JWT authentication & authorization

👨‍💼 Employee CRUD operations

🛡 Role-based access control (Admin/User)

✅ Input validation & exception handling

📊 Pagination & sorting

🗄 MySQL + JPA/Hibernate integration

🛠 Tech Stack

Backend: Spring Boot, Spring Security, JPA, Hibernate

Database: MySQL

Authentication: JWT (JSON Web Token)

Build Tool: Maven

📂 Project Structure
src/main/java/com/example/employee
 ├── controller/     # REST Controllers
 ├── entity/         # Entities (Employee, User, Role)
 ├── repository/     # JPA Repositories
 ├── service/        # Business Logic
 ├── security/       # JWT + Spring Security Config
 └── exception/      # Custom Exceptions

⚡ Getting Started
1. Clone Repository
git clone https://github.com/harsh-w-s/Secure-Employee-Management.git

2. Configure Database

Edit application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=Harsh-w-s
spring.datasource.password=********
spring.jpa.hibernate.ddl-auto=update

3. Run Application
mvn spring-boot:run

📡 API Endpoints
🔑 Auth APIs

POST /api/auth/register → Register new user

POST /api/auth/login → Login & get JWT token

👨‍💼 Employee APIs

GET /api/employees → Fetch all employees (JWT required)

POST /api/employees → Add new employee (Admin only)

PUT /api/employees/{id} → Update employee (Admin only)

DELETE /api/employees/{id} → Delete employee (Admin only)

🔒 Authentication & Roles

Use JWT in the header:

Authorization: Bearer <token>


Roles:

ROLE_ADMIN → Full access (CRUD)

ROLE_USER → Read-only access

🚀 Future Enhancements

Add JUnit & Mockito tests

Docker support for deployment

CI/CD pipeline with GitHub Actions

Frontend integration (React/Angular)
