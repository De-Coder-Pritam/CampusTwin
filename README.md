<div align="center">

```
 ██████╗ █████╗ ███╗   ███╗██████╗ ██╗   ██╗███████╗████████╗██╗    ██╗██╗███╗   ██╗
██╔════╝██╔══██╗████╗ ████║██╔══██╗██║   ██║██╔════╝╚══██╔══╝██║    ██║██║████╗  ██║
██║     ███████║██╔████╔██║██████╔╝██║   ██║███████╗   ██║   ██║ █╗ ██║██║██╔██╗ ██║
██║     ██╔══██║██║╚██╔╝██║██╔═══╝ ██║   ██║╚════██║   ██║   ██║███╗██║██║██║╚██╗██║
╚██████╗██║  ██║██║ ╚═╝ ██║██║     ╚██████╔╝███████║   ██║   ╚███╔███╔╝██║██║ ╚████║
 ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝      ╚═════╝ ╚══════╝   ╚═╝    ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝
```

# CampusTwin — AI Driven Smart Campus Digital Twin

### _A real-time intelligent platform transforming campus management through microservices, live monitoring, and AI-powered risk prediction_

<br/>

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![JWT](https://img.shields.io/badge/JWT-Auth-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)](https://jwt.io/)
[![License](https://img.shields.io/badge/License-Academic-blue?style=for-the-badge)](./LICENSE)

<br/>

> **CAP-599 | Master of Computer Applications**
> Lovely Professional University, Phagwara, Punjab — 144411
> Session: 2025–2027

</div>

---

## 👥 Team

| Name                 | Registration No. | Role                          |
| -------------------- | ---------------- | ----------------------------- |
| Pritam Kumar Branwal | 12503898         | Team Lead & Backend Architect |
| Abhishek Mishra      | 12526538         | Microservices Developer       |
| Aryan Guleria        | 12522004         | Security & Auth Developer     |
| Dilip Kumar          | 12520416         | Monitoring Services Developer |

**Project Guide:** Dr. Tarandeep Singh Walia (ID: 25153)
**School of Computer Applications**, Lovely Professional University

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Problem Statement](#-problem-statement)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Microservices](#-microservices)
- [Repository Structure](#-repository-structure)
- [Getting Started](#-getting-started)
- [API Reference](#-api-reference)
- [Documents](#-documents)
- [Future Scope](#-future-scope)

---

## 🌐 Overview

**CampusTwin** is an AI-Driven Smart Campus Digital Twin — a real-time virtual replica of the Lovely Professional University campus that continuously mirrors physical campus activities.

The system integrates **Artificial Intelligence**, **Event-Driven Microservices**, and **Reactive Programming** to transform traditional campus management from a _reactive complaint-based model_ into a _predictive, data-driven, proactive management system_.

```
Traditional Campus Management          CampusTwin
─────────────────────────────    VS    ────────────────────────────
❌ Manual incident reporting           ✅ Real-time digital twin
❌ Phone-based communication           ✅ Instant multi-role alerts
❌ Fragmented departments              ✅ Centralized monitoring
❌ Static dashboards                   ✅ Live interactive map
❌ Reactive — act after damage         ✅ Proactive — predict & prevent
❌ No data insights                    ✅ AI-powered risk assessment
```

---

## ⚠️ Problem Statement

University campuses function like small cities — thousands of students, faculty, and staff interact with complex infrastructure daily. Existing systems struggle with:

| Challenge                                    | Impact                           |
| -------------------------------------------- | -------------------------------- |
| 🚑 Medical emergencies with delayed response | Risk to student health & life    |
| 🔐 Security breaches going undetected        | Safety threats on campus         |
| 🗑️ Overflowing garbage bins                  | Unhygienic campus environment    |
| 👥 Overcrowding during events                | Safety hazards, crowd crush risk |
| 📞 Fragmented communication channels         | Coordination failures            |
| 📊 No predictive capabilities                | Always reactive, never proactive |

---

## 🏗️ Architecture

CampusTwin follows an **Event-Driven Microservices Architecture** where each service is independently deployable and communicates asynchronously.

```
┌─────────────────────────────────────────────────────────────────┐
│                        CLIENT LAYER                             │
│          React Dashboard  ·  Mobile App  ·  Admin Portal        │
└──────────────────────────┬──────────────────────────────────────┘
                           │ HTTP / WebSocket
┌──────────────────────────▼──────────────────────────────────────┐
│                      API GATEWAY                                │
│              Spring Cloud Gateway · JWT Validation              │
└──┬──────────┬──────────┬──────────┬──────────┬─────────────────┘
   │          │          │          │          │
┌──▼──┐  ┌───▼──┐  ┌────▼──┐  ┌───▼──┐  ┌────▼──────┐
│AUTH │  │INCID-│  │GARBAGE│  │CROWD │  │  AI RISK  │
│SERV │  │ENT   │  │SERVICE│  │SERV  │  │  SERVICE  │
│:8081│  │:8082 │  │:8083  │  │:8084 │  │  :8085    │
└──┬──┘  └───┬──┘  └────┬──┘  └───┬──┘  └────┬──────┘
   │          │          │          │          │
   └──────────┴──────────┴──────────┴──────────┘
                          │
            ┌─────────────▼─────────────┐
            │      MESSAGE BROKER        │
            │   Apache Kafka / RabbitMQ  │
            └─────────────┬─────────────┘
                          │
        ┌─────────────────▼──────────────────┐
        │          DATA LAYER                 │
        │  PostgreSQL · MongoDB · Redis       │
        └────────────────────────────────────┘
```

---

## 🛠️ Tech Stack

### Backend

| Technology      | Version | Purpose                        |
| --------------- | ------- | ------------------------------ |
| Java            | 21      | Primary language               |
| Spring Boot     | 3.5.x   | Microservice framework         |
| Spring Security | 6.x     | Authentication & authorization |
| Spring Data JPA | 3.x     | Database ORM                   |
| Spring WebFlux  | 6.x     | Reactive streaming             |
| jjwt            | 0.12.5  | JWT token management           |
| Lombok          | Latest  | Boilerplate reduction          |

### Database

| Technology    | Purpose                                        |
| ------------- | ---------------------------------------------- |
| PostgreSQL 15 | Primary relational DB (users, incidents, bins) |
| MongoDB       | Crowd data, sensor logs                        |
| Redis         | Caching, sessions                              |
| InfluxDB      | Time-series IoT metrics                        |

### Infrastructure

| Technology       | Purpose                                |
| ---------------- | -------------------------------------- |
| Apache Kafka     | Async event streaming between services |
| Docker + Compose | Containerization                       |
| Eureka           | Service discovery                      |
| Zipkin           | Distributed tracing                    |

### Frontend _(Planned)_

| Technology | Purpose                |
| ---------- | ---------------------- |
| React.js   | Dashboard UI           |
| Leaflet.js | Interactive campus map |
| Chart.js   | Analytics & heatmaps   |

---

## 🧩 Microservices

### 1. Auth Service `:8081` ✅ Complete

> Handles all authentication, authorization, and user management

**Features:**

- JWT Access Token (1 hour) + Refresh Token (7 days)
- 5 Role system — `STUDENT`, `ADMIN`, `SECURITY`, `MEDICAL`, `MAINTENANCE`
- `@lpu.in` email domain restriction
- Public registration → always `STUDENT` role
- Admin-only staff registration with role assignment
- DataSeeder → default admin on startup
- BCrypt password encryption (strength 12)
- Refresh token rotation
- Role-based API access via `@PreAuthorize`

**Key Endpoints:**

```
POST /api/auth/register          → Public student registration
POST /api/auth/login             → Login (email or university ID)
POST /api/auth/refresh           → Rotate access token
POST /api/auth/logout            → Invalidate refresh token
GET  /api/auth/me                → Current user profile
POST /api/auth/admin/register    → Admin creates staff accounts
GET  /api/auth/admin/users       → List all users (Admin only)
```

---

### 2. Incident Service `:8082` ✅ Complete

> Real-time incident reporting and tracking across campus

**Features:**

- 5 incident types — `MEDICAL`, `SECURITY`, `MAINTENANCE`, `FIRE`, `CROWD`
- Full lifecycle — `REPORTED → ACKNOWLEDGED → IN_PROGRESS → RESOLVED → CLOSED`
- Severity rating (1–5)
- Auto-assign staff on first status update
- Role-based visibility — each role sees their domain
- Closed incident protection — cannot re-open
- `resolvedAt` timestamp tracking

**Key Endpoints:**

```
POST /api/incidents              → Report new incident
GET  /api/incidents/my           → My reported incidents
GET  /api/incidents/type/{type}  → By incident type
GET  /api/incidents/assigned     → My assigned incidents
PUT  /api/incidents/{id}/status  → Update status + notes
GET  /api/incidents/admin/all    → All incidents (Admin)
```

---

### 3. Garbage Monitoring Service `:8083` 🚧 In Progress

> Real-time garbage bin fill-level monitoring with map integration

**Features:**

- Bin fill simulation via scheduler (every 4 minutes)
- Status: `GREEN` (0–70%) / `RED` (71–100%)
- GPS coordinates per bin for map visualization
- Maintenance staff can mark bins as empty
- Admin manages bin registry
- DataSeeder → 10 LPU campus bins pre-loaded

**Key Endpoints:**

```
POST /api/bins                   → Add bin (Admin)
PUT  /api/bins/{id}              → Update bin details (Admin)
GET  /api/bins                   → All active bins (map data)
GET  /api/bins/red               → Only RED bins (Maintenance)
PUT  /api/bins/{id}/empty        → Mark bin as emptied (Maintenance)
DELETE /api/bins/{id}            → Deactivate bin (Admin)
```

---

### 4. Crowd Monitoring Service `:8084` 📋 Planned

> Crowd density simulation and hotspot detection

**Planned Features:**

- Zone-based crowd density tracking
- Heatmap data generation
- Peak hour simulation
- Overcrowding alerts

---

### 5. AI Risk Prediction Service `:8085` 📋 Planned

> ML-based incident severity classification and risk assessment

**Planned Features:**

- Incident severity auto-classification
- Risk level prediction by location and time
- Priority queue for emergency response
- Pattern detection from historical data

---

## 📁 Repository Structure

```
campustwin/
│
├── 📄 README.md                          ← You are here
│
├── 📂 docs/
│   ├── 📄 synopsis.pdf                   ← Project Synopsis (CAP-599)
│   └── 📄 srs.pdf                        ← Software Requirements Specification
│
├── 📂 auth-service/                      ✅ Complete
│   ├── src/main/java/com/campustwin/auth/
│   │   ├── entity/          (User, Role, RefreshToken)
│   │   ├── repository/      (UserRepository, RefreshTokenRepository)
│   │   ├── dto/             (RegisterRequest, LoginRequest, AuthResponse...)
│   │   ├── security/        (JwtService, JwtAuthenticationFilter)
│   │   ├── config/          (SecurityConfig, DataSeeder)
│   │   ├── service/         (AuthService, RefreshTokenService)
│   │   ├── controller/      (AuthController)
│   │   └── exception/       (GlobalExceptionHandler)
│   └── src/main/resources/
│       └── application.properties
│
├── 📂 incident-service/                  ✅ Complete
│   ├── src/main/java/com/campustwin/incident/
│   │   ├── entity/          (Incident, IncidentType, IncidentStatus)
│   │   ├── repository/      (IncidentRepository)
│   │   ├── dto/             (CreateIncidentRequest, UpdateIncidentRequest...)
│   │   ├── security/        (JwtService, JwtAuthenticationFilter)
│   │   ├── config/          (SecurityConfig)
│   │   ├── service/         (IncidentService)
│   │   ├── controller/      (IncidentController)
│   │   └── exception/       (GlobalExceptionHandler)
│   └── src/main/resources/
│       └── application.properties
│
├── 📂 garbage-service/                   🚧 In Progress
│   ├── src/main/java/com/campustwin/garbage/
│   │   ├── entity/          (GarbageBin, BinStatus)
│   │   ├── repository/      (GarbageBinRepository)
│   │   ├── dto/             (CreateBinRequest, UpdateBinRequest, BinResponse)
│   │   ├── security/        (JwtService, JwtAuthenticationFilter)
│   │   ├── config/          (SecurityConfig, DataSeeder)
│   │   ├── scheduler/       (GarbageFillSimulator)
│   │   ├── service/         (GarbageBinService)
│   │   ├── controller/      (GarbageBinController)
│   │   └── exception/       (GlobalExceptionHandler)
│   └── src/main/resources/
│       └── application.properties
│
├── 📂 crowd-service/                     📋 Planned
└── 📂 ai-risk-service/                   📋 Planned
```

---

## 🚀 Getting Started

### Prerequisites

| Tool          | Version | Download                                    |
| ------------- | ------- | ------------------------------------------- |
| JDK           | 21+     | [Download](https://adoptium.net/)           |
| Maven         | 3.9+    | [Download](https://maven.apache.org/)       |
| PostgreSQL    | 15+     | [Download](https://www.postgresql.org/)     |
| IntelliJ IDEA | Any     | [Download](https://www.jetbrains.com/idea/) |
| Postman       | Any     | [Download](https://www.postman.com/)        |

### Database Setup

```sql
-- Run in PostgreSQL
CREATE DATABASE campus_auth_db;
CREATE DATABASE campus_incident_db;
CREATE DATABASE campus_garbage_db;
```

### Running Services

```bash
# Each service runs independently in IntelliJ
# Or via Maven:

cd auth-service
mvn spring-boot:run        # starts on :8081

cd incident-service
mvn spring-boot:run        # starts on :8082

cd garbage-service
mvn spring-boot:run        # starts on :8083
```

### Default Admin Credentials

```
Email:        admin@lpu.in
Password:     Admin@1234
University ID: ADMIN001
```

> ⚠️ Change password in production. Admin is auto-seeded on first startup.

### Service Ports

| Service          | Port | Status         |
| ---------------- | ---- | -------------- |
| Auth Service     | 8081 | ✅ Running     |
| Incident Service | 8082 | ✅ Running     |
| Garbage Service  | 8083 | 🚧 In Progress |
| Crowd Service    | 8084 | 📋 Planned     |
| AI Risk Service  | 8085 | 📋 Planned     |

---

## 📡 API Reference

### Authentication (Base: `localhost:8081`)

```http
POST   /api/auth/register             Public
POST   /api/auth/login                Public
POST   /api/auth/refresh              Public
POST   /api/auth/logout               🔐 JWT Required
GET    /api/auth/me                   🔐 JWT Required
POST   /api/auth/admin/register       🔐 ADMIN only
GET    /api/auth/admin/users          🔐 ADMIN only
PATCH  /api/auth/admin/users/{id}/role 🔐 ADMIN only
```

### Incidents (Base: `localhost:8082`)

```http
POST   /api/incidents                 🔐 All roles
GET    /api/incidents/my              🔐 All roles
GET    /api/incidents/{id}            🔐 All roles
GET    /api/incidents/type/{type}     🔐 Staff + Admin
GET    /api/incidents/assigned        🔐 Staff + Admin
PUT    /api/incidents/{id}/status     🔐 Staff + Admin
GET    /api/incidents/admin/all       🔐 ADMIN only
GET    /api/incidents/admin/status/{status} 🔐 ADMIN only
```

### Garbage Bins (Base: `localhost:8083`)

```http
GET    /api/bins                      🔐 All roles
GET    /api/bins/{id}                 🔐 All roles
GET    /api/bins/red                  🔐 MAINTENANCE + ADMIN
POST   /api/bins                      🔐 ADMIN only
PUT    /api/bins/{id}                 🔐 ADMIN only
DELETE /api/bins/{id}                 🔐 ADMIN only
PUT    /api/bins/{id}/empty           🔐 MAINTENANCE + ADMIN
```

---

## 📋 User Roles

| Role          | Capabilities                                                 |
| ------------- | ------------------------------------------------------------ |
| `STUDENT`     | Report incidents, view own incidents, view bin/crowd status  |
| `SECURITY`    | All student rights + manage SECURITY incidents               |
| `MEDICAL`     | All student rights + manage MEDICAL incidents                |
| `MAINTENANCE` | All student rights + manage bins, MAINTENANCE incidents      |
| `ADMIN`       | Full system access — all services, all data, user management |

---

## 🔐 Security Model

```
Every request flow:
─────────────────
Request → JwtAuthenticationFilter
              ↓
         Extract email + role from JWT
              ↓
         Set SecurityContext
              ↓
         @PreAuthorize checks role
              ↓
         Controller → Service → DB
```

- **Access Token** — 1 hour validity, contains email + role
- **Refresh Token** — 7 days, stored in DB, rotated on use
- **Email Restriction** — only `@lpu.in` domains allowed
- **Password** — BCrypt encrypted, never returned in responses
- **Stateless** — no server-side sessions

---

## 🔮 Future Scope

| Feature                 | Description                                   |
| ----------------------- | --------------------------------------------- |
| 🌐 API Gateway          | Single entry point — Spring Cloud Gateway     |
| 🤖 AI Risk Prediction   | ML model for incident severity classification |
| 📱 Mobile App           | Student-facing React Native app               |
| 🗺️ Live Campus Map      | Real-time bin status + incident heatmap       |
| 📡 Real IoT Integration | Actual sensor data instead of simulation      |
| 📹 CCTV Crowd Detection | Computer vision for crowd density             |
| 🔔 Push Notifications   | Real-time alerts via WebSocket/SSE            |
| 🏙️ Multi-Campus         | Expand beyond LPU                             |

---

## 📄 Documents

| Document         | Description                         | Link                        |
| ---------------- | ----------------------------------- | --------------------------- |
| Project Synopsis | Initial project proposal (CAP-599)  | [View](./docs/synopsis.pdf) |
| SRS Document     | Software Requirements Specification | [View](./docs/srs.pdf)      |

---

## 📊 Development Progress

```
Auth Service        ████████████████████  100% ✅
Incident Service    ████████████████████  100% ✅
Garbage Service     ████████░░░░░░░░░░░░   40% 🚧
Crowd Service       ░░░░░░░░░░░░░░░░░░░░    0% 📋
AI Risk Service     ░░░░░░░░░░░░░░░░░░░░    0% 📋
Frontend Dashboard  ░░░░░░░░░░░░░░░░░░░░    0% 📋
API Gateway         ░░░░░░░░░░░░░░░░░░░░    0% 📋
```

---

<div align="center">

**Built with ❤️ at Lovely Professional University**

_CampusTwin — Making campuses smarter, safer, and more efficient_

`School of Computer Applications` · `MCA 2025–2027` · `CAP-599`

</div>
