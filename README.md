# Lab 14: Securing a Java Spring Web Application

**Student Name:** Ahmet Gunes  
**Student ID:** 47463

<div align="center">
  <p align="center">
    <img width="400" src="https://github.com/spring-projects/spring-framework/raw/main/src/docs/spring-framework.png" alt="Spring Framework">
  </p>
</div>

## 🎯 Overview
A Spring Boot application with blog management system and comprehensive security measures. Built on Lab 13, adding user data management, authentication, and authorization.

## ⚡ Features
- **Blog Management**
  - CRUD operations for blog posts
  - User-specific post management
  - SQLite database with Flyway migrations

- **Security**
  - JWT token authentication
  - BCrypt password hashing (min. 14 chars)
  - Role-based access control
  - Global exception handling
  - SLF4J logging system

- **Testing**
  - Unit tests with JUnit & Mockito
  - Service layer test coverage
  - Security validation tests

## 🚀 API Endpoints

### Auth
```
POST /api/auth/register - Register
POST /api/auth/login    - Login
POST /api/auth/logout   - Logout
```

### Blog Posts
```
GET    /api/posts      - List posts
GET    /api/posts/{id} - Get post
POST   /api/posts      - Create post
PUT    /api/posts/{id} - Update post
DELETE /api/posts/{id} - Delete post
```

## 📦 Tech Stack
- Spring Boot 3.1.0
- Spring Security
- SQLite & JPA
- Flyway
- JUnit 5
- SLF4J & Logback

## 🔧 Setup & Run
```bash
git clone <repo-url>
cd spring-security-blog
cp .env.example .env  # Configure DB
mvn spring-boot:run   # Runs on :8081
