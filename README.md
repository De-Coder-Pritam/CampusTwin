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

<br/>

**Built by Pritam Kumar Branwal**

</div>

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Microservices](#-microservices)
- [Repository Structure](#-repository-structure)
- [Getting Started](#-getting-started)
- [API Reference](#-api-reference)
- [Future Scope](#-future-scope)

---

## 🌐 Overview

**CampusTwin** is an AI-Driven Smart Campus Digital Twin — a real-time virtual replica of a university campus that continuously mirrors physical campus activities.

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

## 🏗️ Architecture

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
   └──────────┴──────────┴──────────┴──────────┘
                          │
            ┌─────────────▼─────────────┐
            │      MESSAGE BROKER        │
            │        Apache Kafka        │
            └─────────────┬─────────────┘
                          │
        ┌─────────────────▼──────────────────┐
        │          DATA LAYER                 │
        │  PostgreSQL · MongoDB · Redis       │
        └────────────────────────────────────┘
```

---

## 🛠️ Tech Stack

| Layer     | Technology                   | Purpose                |
| --------- | ---------------------------- | ---------------------- |
| Language  | Java 21                      | Primary language       |
| Framework | Spring Boot 3.5.x            | Microservice framework |
| Security  | Spring Security + JWT 0.12.5 | Auth & authorization   |
| ORM       | Spring Data JPA              | Database operations    |
| Streaming | Spring WebFlux               | Reactive live updates  |
| Database  | PostgreSQL                   | Primary relational DB  |
| Database  | MongoDB                      | Sensor & crowd logs    |
| Cache     | Redis                        | Sessions & caching     |
| Messaging | Apache Kafka                 | Async event streaming  |
| Tooling   | Lombok, Maven                | Dev productivity       |

---

## 🧩 Microservices

### 1. Auth Service `:8081` ✅ Complete

- JWT Access Token (1 hr) + Refresh Token (7 days)
- 5 roles — `STUDENT`, `ADMIN`, `SECURITY`, `MEDICAL`, `MAINTENANCE`
- Domain restriction — only `@lpu.in` emails
- Public registration always assigns `STUDENT` role
- Admin creates staff accounts with specific roles
- BCrypt password encryption
- DataSeeder — default admin on first startup

### 2. Incident Service `:8082` ✅ Complete

- 5 types — `MEDICAL`, `SECURITY`, `MAINTENANCE`, `FIRE`, `CROWD`
- Full lifecycle — `REPORTED → ACKNOWLEDGED → IN_PROGRESS → RESOLVED → CLOSED`
- Severity rating 1–5
- Auto-assign staff on first update
- Role-based visibility per department
- `resolvedAt` timestamp on resolution

### 3. Garbage Monitoring Service `:8083` 🚧 In Progress

- Bin fill simulation via scheduler (every 4 minutes)
- `GREEN` (0–70%) / `RED` (71–100%) status
- GPS coordinates per bin for map integration
- Maintenance staff empties bins via API
- Admin manages bin registry
- 10 campus bins pre-seeded with coordinates

### 4. Crowd Monitoring Service `:8084` 📋 Planned

- Zone-based crowd density tracking
- Heatmap data generation
- Peak hour simulation
- Overcrowding alerts

### 5. AI Risk Prediction Service `:8085` 📋 Planned

- ML-based incident severity classification
- Risk level prediction by location and time
- Pattern detection from historical data

---

## 📁 Repository Structure

```
campustwin/
│
├── 📄 README.md
├── 📂 docs/
│   ├── synopsis.pdf
│   └── srs.pdf
│
├── 📂 auth-service/                ✅ Complete
├── 📂 incident-service/            ✅ Complete
├── 📂 garbage-service/             🚧 In Progress
├── 📂 crowd-service/               📋 Planned
└── 📂 ai-risk-service/             📋 Planned
```

---

## 🚀 Getting Started

### Prerequisites

- JDK 21+
- Maven 3.9+
- PostgreSQL 15+
- IntelliJ IDEA

### Database Setup

```sql
CREATE DATABASE campus_auth_db;
CREATE DATABASE campus_incident_db;
CREATE DATABASE campus_garbage_db;
```

### Running Services

```bash
cd auth-service && mvn spring-boot:run       # :8081
cd incident-service && mvn spring-boot:run   # :8082
cd garbage-service && mvn spring-boot:run    # :8083
```

### Default Admin

```
Email:        admin@lpu.in
Password:     Admin@1234
```

---

## 📡 API Reference

### Auth Service — `localhost:8081`

```http
POST   /api/auth/register                Public
POST   /api/auth/login                   Public
POST   /api/auth/refresh                 Public
POST   /api/auth/logout                  🔐 JWT
GET    /api/auth/me                      🔐 JWT
POST   /api/auth/admin/register          🔐 ADMIN
GET    /api/auth/admin/users             🔐 ADMIN
PATCH  /api/auth/admin/users/{id}/role   🔐 ADMIN
```

### Incident Service — `localhost:8082`

```http
POST   /api/incidents                    🔐 All roles
GET    /api/incidents/my                 🔐 All roles
GET    /api/incidents/{id}               🔐 All roles
GET    /api/incidents/type/{type}        🔐 Staff + Admin
GET    /api/incidents/assigned           🔐 Staff + Admin
PUT    /api/incidents/{id}/status        🔐 Staff + Admin
GET    /api/incidents/admin/all          🔐 ADMIN
GET    /api/incidents/admin/status/{s}   🔐 ADMIN
```

### Garbage Service — `localhost:8083`

```http
GET    /api/bins                         🔐 All roles
GET    /api/bins/{id}                    🔐 All roles
GET    /api/bins/red                     🔐 MAINTENANCE + ADMIN
POST   /api/bins                         🔐 ADMIN
PUT    /api/bins/{id}                    🔐 ADMIN
DELETE /api/bins/{id}                    🔐 ADMIN
PUT    /api/bins/{id}/empty              🔐 MAINTENANCE + ADMIN
```

---

## 📊 Progress

```
Auth Service        ████████████████████  100% ✅
Incident Service    ████████████████████  100% ✅
Garbage Service     ████████░░░░░░░░░░░░   40% 🚧
Crowd Service       ░░░░░░░░░░░░░░░░░░░░    0% 📋
AI Risk Service     ░░░░░░░░░░░░░░░░░░░░    0% 📋
Frontend Dashboard  ░░░░░░░░░░░░░░░░░░░░    0% 📋
```

---

## 🔮 Future Scope

- 🌐 API Gateway — Spring Cloud Gateway
- 🤖 AI Risk Prediction — ML severity classification
- 📱 Mobile App — React Native
- 🗺️ Live Campus Map — real-time bin + incident overlay
- 📡 Real IoT Integration — actual sensor data
- 📹 CCTV Crowd Detection — computer vision
- 🔔 Push Notifications — WebSocket / SSE
- 🏙️ Multi-Campus Support

---

<div align="center">

_CampusTwin — Making campuses smarter, safer, and more efficient_

**Pritam Kumar Branwal**

</div>
