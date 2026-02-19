# Secure Employee Management System

## Overview

Secure Employee Management System is a backend service for managing employee records with authentication and authorization controls. It is implemented with Spring Boot and exposes RESTful APIs for user registration, login, and employee lifecycle operations.

The application is designed around a layered architecture (controller, service, repository, security) and uses JWT-based authentication with Spring Security.

## Key Capabilities

- JWT-based authentication for stateless API access.
- Role-aware authorization using method-level and request-level security controls.
- Employee CRUD endpoints with validation and pagination support.
- MySQL persistence with Spring Data JPA and Hibernate.
- Password hashing with BCrypt.

## Technology Stack

- **Language**: Java 21
- **Framework**: Spring Boot 3.3.12
- **Security**: Spring Security, JSON Web Tokens (JJWT)
- **Data Access**: Spring Data JPA, Hibernate
- **Database**: MySQL
- **Build Tool**: Maven
- **Testing**: JUnit 5 (Spring Boot Test)

## Project Structure

```text
employeeApp/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/employeeApp/
│   │   │   ├── EmployeeAppApplication.java
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   ├── security/
│   │   │   └── config/
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/example/employeeApp/
│       └── EmployeeAppApplicationTests.java
└── mvnw
```

## Architecture Summary

The request lifecycle is structured as follows:

1. Client authenticates via `/api/auth/login` and receives a JWT.
2. Client includes `Authorization: Bearer <token>` for secured endpoints.
3. `JwtAuthFilter` extracts and validates the token, then populates Spring Security context.
4. Controllers delegate business logic to services.
5. Services interact with repositories for persistence operations.

This separation keeps transport concerns, business logic, and persistence concerns isolated and easier to evolve.

## Prerequisites

- JDK 21
- Maven 3.9+ (or use the included Maven wrapper)
- MySQL 8+

## Setup and Configuration

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd Secure-Employee-Management-System/employeeApp
```

### 2. Configure Database and Application Properties

Update `src/main/resources/application.properties` for your environment:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employeeApp?useSSL=false&serverTimezone=UTC
spring.datasource.username=<db-username>
spring.datasource.password=<db-password>
spring.jpa.hibernate.ddl-auto=update
```

Recommended production practices:

- Move all secrets (database password, JWT secret, etc.) to environment variables or a secret manager.
- Use least-privilege database users.
- Disable SQL logging in production unless required for troubleshooting.

### 3. Build and Run

Using Maven wrapper:

```bash
./mvnw clean spring-boot:run
```

Or with local Maven installation:

```bash
mvn clean spring-boot:run
```

The application starts on `http://localhost:8080` by default.

## Authentication and Authorization

### Registration

`POST /api/auth/register`

Registers a new application user. Passwords are encoded before persistence.

### Login

`POST /api/auth/login`

Authenticates user credentials and returns a JWT token.

### Secured Access

Use the token in request headers:

```http
Authorization: Bearer <jwt-token>
```

Security policy is stateless (`SessionCreationPolicy.STATELESS`), and all non-auth routes require authentication by default.

## API Endpoints

### Authentication

- `POST /api/auth/register`
- `POST /api/auth/login`

### Employee Management

- `GET /employees` — list employees with pagination (`page`, `size`)
- `GET /employees/{id}` — fetch a specific employee
- `POST /employees` — create a new employee
- `PUT /employees/{id}` — update an employee
- `DELETE /employees/{id}` — delete an employee

## Example Requests

### Register User

```bash
curl -X POST "http://localhost:8080/api/auth/register" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "strongPassword123",
    "role": "ADMIN"
  }'
```

### Login

```bash
curl -X POST "http://localhost:8080/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "strongPassword123"
  }'
```

### Access Employees API

```bash
curl -X GET "http://localhost:8080/employees?page=0&size=5" \
  -H "Authorization: Bearer <jwt-token>"
```

## Validation and Error Handling

Domain models include validation annotations (for example, required fields, email format, minimum salary constraints, and date constraints). Invalid request payloads result in standard Spring validation errors.

For production readiness, consider introducing:

- Centralized exception handling (`@ControllerAdvice`).
- Consistent API error response schema with traceable error codes.
- Structured logging and request correlation IDs.

## Testing

Run tests using:

```bash
./mvnw test
```

Current test coverage is minimal and should be expanded with:

- Unit tests for services and JWT utility behavior.
- Integration tests for authentication and authorization flows.
- Repository and controller tests for data and contract validation.

## Operational and Security Notes

Before deploying to production, review the following:

- Externalize credentials and secrets.
- Configure CORS and rate limiting based on client access patterns.
- Enforce transport security (TLS termination) in all environments.
- Add observability (metrics, health checks, logs, traces).
- Introduce CI checks for tests, static analysis, and dependency vulnerabilities.

## Roadmap Suggestions

- Add OpenAPI/Swagger documentation.
- Introduce API versioning and backward-compatibility policies.
- Containerize with Docker and provide deployment manifests.
- Add migration tooling (Flyway/Liquibase) for controlled schema changes.
- Expand role model and permissions into finer-grained access controls.

## License

No license file is currently present in this repository. Add a license before public distribution.
