# 🚀 MeetGenie

> AI-Powered Meeting Platform built with Spring Boot, Spring Security, PostgreSQL, and JWT Authentication.

MeetGenie is a full-stack meeting platform currently under active development. The goal of this project is to build a production-grade application that enables secure online meetings with AI-powered features such as meeting summaries, searchable transcripts, emotion analysis, and custom AI meeting agents.

This repository currently contains the complete authentication module developed using Spring Boot and Spring Security.

---

# ✨ Current Features

## 🔐 Authentication & Security

- User Registration API
- User Login API
- JWT-based Authentication
- BCrypt Password Hashing
- Protected REST Endpoints
- Stateless Authentication
- Spring Security Integration

---

## 🏗 Backend Architecture

- Layered Architecture
    - Controller
    - Service
    - Repository
- DTO Pattern
- Dependency Injection
- Spring Data JPA
- Hibernate ORM
- Global Exception Handling
- Request Validation

---

## 🗄 Database

- PostgreSQL
- User Entity
- Automatic Table Creation using Hibernate
- Secure Password Storage (BCrypt)

---

## 📄 API Documentation

Interactive API documentation is available using **Swagger/OpenAPI**.

Current APIs:

- POST `/api/auth/register`
- POST `/api/auth/login`
- GET `/api/test`

---

# 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Database Access |
| Hibernate | ORM |
| PostgreSQL | Relational Database |
| JWT | Stateless Authentication |
| BCrypt | Password Encryption |
| Maven | Dependency Management |
| Swagger/OpenAPI | API Documentation |
| Postman | API Testing |
| Git & GitHub | Version Control |

---

# 📂 Project Structure

```
src
└── main
    └── java
        └── com.meetgenie.backend
            ├── config
            ├── controller
            ├── dto
            ├── entity
            ├── exception
            ├── repository
            ├── security
            ├── service
            └── BackendApplication
```

---

# 🔄 Authentication Flow

```
Client
   │
   ▼
Login Request
   │
   ▼
AuthenticationManager
   │
   ▼
Spring Security
   │
   ▼
CustomUserDetailsService
   │
   ▼
PostgreSQL
   │
   ▼
JWT Generated
   │
   ▼
Client Stores JWT
   │
   ▼
Protected API Request
   │
   ▼
JWT Filter
   │
   ▼
Token Validation
   │
   ▼
Authenticated Response
```

---

# 🚧 Roadmap

## ✅ Phase 1 (Completed)

- Authentication Module
- JWT Authentication
- Spring Security
- BCrypt Password Hashing
- Swagger Documentation

---

## 🔄 Phase 2 (In Progress)

- Meeting Entity
- Create Meeting API
- Join Meeting API
- My Meetings API
- Delete Meeting API

---

## 🔜 Upcoming Features

- Video Conferencing
- AI Meeting Summaries
- Searchable Meeting Transcripts
- Custom AI Meeting Agents
- Emotion Analysis
- Meeting Recordings
- Role-Based Access Control (RBAC)
- Cloud Deployment (AWS)

---

# ▶ Running the Project

## Clone Repository

```bash
git clone https://github.com/himanshu8872/MeetGenie.git
```

---

## Navigate

```bash
cd MeetGenie
```

---

## Configure PostgreSQL

Update:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/meetgenie
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
```

---

## Run

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

# 📖 API Documentation

After running the project:

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📸 Current Screenshots

- Swagger API Documentation
- JWT Login API
- Protected Endpoint Authentication
- PostgreSQL Database
- Layered Project Architecture

---

# 🎯 Learning Objectives

This project is being developed to gain hands-on experience with:

- Spring Boot
- Spring Security
- JWT Authentication
- Production-grade Backend Development
- REST API Design
- PostgreSQL
- Software Architecture
- Clean Code Principles

---

# 👨‍💻 Author

**Himanshu Mahajan**

B.Tech Computer Engineering

Passionate about Java Backend Development, Spring Boot, and building scalable backend systems.

LinkedIn:
https://www.linkedin.com/in/himanshu-mahajan-6bba49324/

GitHub:
https://github.com/himanshu8872

---

## Recent Progress

- ✅ JWT Authentication
- ✅ Meeting Creation
- ✅ Meeting Join/Leave
- ✅ Meeting Authorization (Participants only)
- 🚧 Sprint 4: Meeting Lifecycle Hardening

⭐ If you find this project interesting, consider giving it a Star!
