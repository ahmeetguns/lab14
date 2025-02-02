# 🔐 Spring Security User Management System

**Student Name:** Ahmet Gunes  
**Student ID:** 47463

---

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen.svg)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Latest-green.svg)
![SQLite](https://img.shields.io/badge/SQLite-3.47.1-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

## 📋 Project Overview

![Spring Framework](https://raw.githubusercontent.com/spring-projects/spring-framework/main/framework-docs/src/docs/spring-framework.png){: align="right" width="100" height="100"}

A comprehensive user management system showcasing modern Java web development practices. This project implements robust security measures using Spring Security, demonstrating industry-standard authentication and authorization mechanisms.

### ✨ Key Features

- 🔒 Secure user authentication & authorization
- 👥 Role-based access control (RBAC)
- 💾 SQLite database with JPA integration
- 🔄 RESTful API architecture
- 🔑 Password encryption with BCrypt
- 🎫 JWT token-based authentication
- 📊 Database migrations with Flyway

## 🛠️ Technology Stack

| Technology | Version | Description |
|------------|---------|-------------|
| Spring Boot | 3.1.0 | Application framework |
| Spring Security | Latest | Security framework |
| Spring Data JPA | Latest | Data persistence |
| SQLite | 3.47.1 | Database |
| Flyway | Latest | Database migration |
| Lombok | Latest | Boilerplate reduction |
| Maven | Latest | Build tool |

## 🚀 Quick Start

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd spring-security-user-management
   ```

2. **Configure Environment**
   ```bash
   cp .env.example .env
   # Edit .env with your database credentials
   ```

3. **Build & Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the Application**
   ```
   http://localhost:8081
   ```

## 📝 API Documentation

### Authentication Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/auth/register` | Register new user | Public |
| POST | `/api/auth/login` | User login | Public |

### User Management Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/users` | List all users | Admin |
| GET | `/api/users/{id}` | Get user details | Admin/Owner |
| PUT | `/api/users/{id}` | Update user | Admin/Owner |
| DELETE | `/api/users/{id}` | Delete user | Admin |

## 🔒 Security Implementation

- ✅ BCrypt password hashing
- ✅ JWT token authentication
- ✅ Role-based authorization
- ✅ Session management
- ✅ CSRF protection
- ✅ XSS prevention
- ✅ Secure HTTP headers

## 📚 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── demo/
│   │               ├── config/
│   │               ├── controller/
│   │               ├── model/
│   │               ├── repository/
│   │               └── service/
│   └── resources/
│       ├── db/migration/
│       └── application.properties
