# 🚀 MeetGenie

> AI-Powered Meeting Platform built with **Spring Boot, Spring Security, JWT, PostgreSQL, WebSockets, and STOMP Messaging**.

MeetGenie is a production-oriented full-stack meeting platform currently under active development. The project focuses on building scalable backend architecture while integrating AI-powered meeting capabilities such as meeting summaries, searchable transcripts, custom AI meeting agents, and real-time collaboration.

The backend is being developed incrementally using industry-standard software engineering practices, including layered architecture, clean code principles, secure authentication, RESTful APIs, and real-time communication.

---

# ✨ Current Features

## 🔐 Authentication & Security

- User Registration
- User Login
- JWT Authentication
- Stateless Authentication
- BCrypt Password Encryption
- Protected REST APIs
- Spring Security Integration
- Global Exception Handling
- Request Validation

---

## 📅 Meeting Management

### Meeting APIs

- Create Meeting
- Get My Meetings
- Get Meeting by Code
- Delete Meeting

### Participant APIs

- Join Meeting
- Leave Meeting

### Meeting Lifecycle APIs

- Start Meeting
- End Meeting

---

## 🔒 Business Rules Implemented

The backend enforces production-style business validations instead of simple CRUD operations.

- Only the meeting host can start a meeting
- Only the meeting host can end a meeting
- Only the meeting host can delete a meeting
- Hosts cannot leave their own meetings
- Duplicate meeting joins are prevented
- Meetings cannot be started twice
- Meetings cannot be ended before they are started
- JWT-secured meeting operations
- Participant authorization checks

---

## ⚡ Real-Time Communication

Implemented using Spring WebSocket and STOMP.

Current capabilities include:

- WebSocket Configuration
- STOMP Messaging
- Topic-based Publish/Subscribe
- SockJS Fallback Support
- Real-time Message Broadcasting

This serves as the foundation for upcoming video conferencing and WebRTC signaling.

---

# 🏗 Backend Architecture

Designed using production-oriented layered architecture.

```
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
PostgreSQL
```

### Design Patterns

- Layered Architecture
- DTO Pattern
- Dependency Injection
- Repository Pattern
- Service Layer Abstraction
- Enum-based State Management
- Global Exception Handling

---

# 🗄 Database

Database: PostgreSQL

Current Entities

- User
- Meeting
- MeetingParticipant

Features

- Hibernate ORM
- Spring Data JPA
- Automatic Schema Generation
- BCrypt Password Storage
- Entity Relationships

---

# 📄 REST APIs

## Authentication

| Method | Endpoint |
|---------|----------|
| POST | `/api/auth/register` |
| POST | `/api/auth/login` |

---

## Meeting Management

| Method | Endpoint |
|---------|----------|
| POST | `/api/meetings` |
| GET | `/api/meetings` |
| GET | `/api/meetings/{meetingCode}` |
| DELETE | `/api/meetings` |

---

## Meeting Lifecycle

| Method | Endpoint |
|---------|----------|
| POST | `/api/meetings/join` |
| POST | `/api/meetings/leave` |
| POST | `/api/meetings/start` |
| POST | `/api/meetings/end` |

---

## Test Endpoint

| Method | Endpoint |
|---------|----------|
| GET | `/api/test` |

---

# ⚡ WebSocket Endpoints

| Endpoint | Purpose |
|----------|---------|
| `/ws` | WebSocket Handshake |
| `/app/chat` | Client Message Destination |
| `/topic/messages` | Broadcast Topic |

---

# 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| Spring WebSocket | Real-time Communication |
| STOMP | Messaging Protocol |
| SockJS | WebSocket Fallback |
| Spring Data JPA | Database Access |
| Hibernate | ORM |
| PostgreSQL | Database |
| JWT | Authentication |
| BCrypt | Password Encryption |
| Maven | Dependency Management |
| Swagger / OpenAPI | API Documentation |
| Postman | API Testing |
| Git & GitHub | Version Control |

---

# 📂 Project Structure

```
src
└── main
    ├── java
    │   └── com.meetgenie.backend
    │       ├── config
    │       ├── controller
    │       ├── dto
    │       ├── entity
    │       ├── exception
    │       ├── repository
    │       ├── security
    │       ├── service
    │       └── BackendApplication
    │
    └── resources
        ├── static
        └── application.properties
```

---

# 🔐 Authentication Flow

```
Client

      │

Login Request

      │

AuthenticationManager

      │

Spring Security

      │

CustomUserDetailsService

      │

PostgreSQL

      │

JWT Generated

      │

Client Stores Token

      │

Protected Request

      │

JWT Filter

      │

Authenticated Response
```

---

# ⚡ WebSocket Messaging Flow

```
Browser

      │

WebSocket Connection

      │

SockJS

      │

STOMP

      │

/app/chat

      │

@MessageMapping

      │

Spring Message Broker

      │

/topic/messages

      │

All Connected Clients
```

---

# 🚀 Development Progress

## ✅ Sprint 0

- Spring Boot Setup
- PostgreSQL Configuration
- Layered Architecture
- GitHub Repository

---

## ✅ Sprint 1

Authentication Module

- JWT Authentication
- Spring Security
- BCrypt
- Swagger Documentation
- Protected APIs

---

## ✅ Sprint 2

Meeting Management

- Create Meeting
- Get My Meetings
- Meeting Code Generation
- Meeting Lookup

---

## ✅ Sprint 3

Participant Management

- Join Meeting
- Leave Meeting
- MeetingParticipant Entity
- Participant Authorization

---

## ✅ Sprint 4

Meeting Lifecycle

- Start Meeting
- End Meeting
- Delete Meeting
- Meeting State Management
- Authorization Rules
- Backend Refactoring
- Enum-based Status
- Code Cleanup

---

## 🚧 Sprint 5 (Current)

Real-Time Communication

Completed

- WebSocket Configuration
- STOMP Messaging
- Publish / Subscribe Messaging
- SockJS Integration

In Progress

- Meeting Rooms
- User Presence
- Join / Leave Notifications
- WebRTC Signaling

---

# 🔜 Upcoming Features

## 🎥 Real-Time Meetings

- Meeting Rooms
- User Presence
- WebRTC Signaling
- Video Calling

---

## 🤖 AI Features

- AI Meeting Summaries
- Searchable Meeting Transcripts
- AI Meeting Agent
- Action Item Extraction
- Speaker Identification

---

## ☁ Deployment

- Docker
- AWS
- CI/CD
- Production Logging

---

# ▶ Running the Project

## Clone Repository

```bash
git clone https://github.com/himanshu8872/MeetGenie.git
```

## Navigate

```bash
cd MeetGenie
```

## Configure PostgreSQL

Update

```
src/main/resources/application.properties
```

Example

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/meetgenie
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
```

## Run

Windows

```bash
.\mvnw spring-boot:run
```

Linux / macOS

```bash
./mvnw spring-boot:run
```

---

# 📖 API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📸 Current Project Screenshots

- JWT Authentication
- Swagger API Documentation
- Meeting Management APIs
- Meeting Lifecycle APIs
- PostgreSQL Database
- WebSocket STOMP Messaging
- Project Architecture

---

# 🎯 Learning Goals

This project is being built to gain hands-on experience with:

- Spring Boot
- Spring Security
- JWT Authentication
- WebSockets
- STOMP Messaging
- WebRTC
- REST API Design
- PostgreSQL
- Software Architecture
- Clean Code
- Production Backend Development

---

# 👨‍💻 Author

**Himanshu Mahajan**

B.Tech Computer Engineering

Aspiring Java Backend Developer passionate about building scalable backend systems, real-time applications, and AI-powered software.

### Connect with me

**LinkedIn**

https://www.linkedin.com/in/himanshu-mahajan-6bba49324/

**GitHub**

https://github.com/himanshu8872

---

⭐ If you found this project interesting, consider giving it a star!