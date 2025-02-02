# Lab 14: Securing a Java Spring Web Application

**Student Name:** Ahmet Gunes  
**Student ID:** 47463

---

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen.svg)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Latest-green.svg)
![SQLite](https://img.shields.io/badge/SQLite-3.47.1-blue.svg)
![JUnit](https://img.shields.io/badge/JUnit-5-orange.svg)

## 📋 Project Overview

<div align="center">
  <p align="center">
    <img width="400" src="https://github.com/spring-projects/spring-framework/raw/main/src/docs/spring-framework.png" alt="Spring Framework">
  </p>
  <h3 align="center">🚀 Spring Security & Blog Management System</h3>
  <p align="center">
    A secure and modern Spring Boot application with JWT authentication and blog management
    <br />
    <a href="#-completed-tasks">View Tasks</a>
    ·
    <a href="#-api-endpoints">API Docs</a>
    ·
    <a href="#-security-features">Security</a>
  </p>
</div>

This project extends Lab 13's basic Spring application by implementing comprehensive security measures and adding a blog post management system. The application demonstrates secure user authentication, authorization, and data management practices using Spring Security and JWT tokens.

## 🎯 Completed Tasks

### Task 1: Database Migration
- Created new `blog_posts` table using Flyway migration
- Implemented table structure with:
  - Primary key (id)
  - Foreign key (user_id) linking to users table
  - Content fields (title, content, status)
  - Metadata fields (created_at, updated_at)

### Task 2: Backend Implementation
- Created complete backend structure:
  - Entity: `BlogPost.java` with JPA annotations
  - Repository: `BlogPostRepository.java` for database operations
  - Service: `BlogPostService.java` with business logic
  - Input validation using Jakarta Validation annotations

### Task 3: Security Implementation
Authentication & Authorization:
- JWT token-based authentication
- Protected endpoints with @PreAuthorize
- User-specific data access control

Access Control:
- @AuthenticationPrincipal for user context
- User data isolation
- Role-based access control

Input Validation:
- Request payload validation
- XSS prevention
- SQL injection protection

Error Handling:
- Global exception handler (@ControllerAdvice)
- User-friendly error messages
- Secure error responses

### Task 4: Security Requirements
Token Management:
- JWT token implementation
- Token invalidation on logout
- HTTP-only cookie storage
- Token refresh mechanism

Password Security:
- BCrypt password hashing
- Strong password policy:
  - Minimum 14 characters
  - Must include uppercase and lowercase letters
  - Must include numbers
  - Must include special characters
  - No common password patterns

Logging:
- Security event logging with SLF4J
- Appropriate log levels (INFO, WARN, ERROR)
- Sensitive data masking in logs

### Task 5: Testing
- Comprehensive unit tests for:
  - User authentication
  - Blog post CRUD operations
  - Access control validation
  - Input validation
- Mock-based testing with JUnit and Mockito

## 🛠️ Technical Stack

| Component | Technology |
|-----------|------------|
| Framework | Spring Boot 3.1.0 |
| Security | Spring Security |
| Database | SQLite |
| ORM | Spring Data JPA |
| Migration | Flyway |
| Testing | JUnit 5, Mockito |
| Logging | SLF4J, Logback |
| Build Tool | Maven |

## 📝 API Endpoints

### Authentication
```
POST /api/auth/register - User registration
POST /api/auth/login    - User login
POST /api/auth/logout   - User logout
```

### Blog Posts
```
GET    /api/posts      - Get user's posts
GET    /api/posts/{id} - Get specific post
POST   /api/posts      - Create new post
PUT    /api/posts/{id} - Update post
DELETE /api/posts/{id} - Delete post
```

## 🚀 Running the Application

1. Clone the repository
```bash
git clone <repository-url>
cd spring-security-blog
```

2. Configure environment
```bash
cp .env.example .env
# Edit .env with your database credentials
```

3. Build and run
```bash
mvn clean install
mvn spring-boot:run
```

4. Access the application
```
http://localhost:8081
```

## 🔒 Security Features

1. **Authentication**
   - JWT token-based authentication
   - Secure token storage in HTTP-only cookies
   - Token refresh mechanism
   - Session invalidation on logout

2. **Authorization**
   - Role-based access control
   - User data isolation
   - Resource-level permissions

3. **Data Security**
   - Password hashing with BCrypt
   - Input validation and sanitization
   - XSS and CSRF protection
   - SQL injection prevention

4. **Error Handling**
   - Global exception handling
   - Secure error messages
   - No stack traces in production

5. **Logging**
   - Security event logging
   - Sensitive data masking
   - Appropriate log levels

## 📚 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── demo/
│   │               ├── config/
│   │               │   └── SecurityConfig.java
│   │               ├── controller/
│   │               │   ├── AuthController.java
│   │               │   └── BlogPostController.java
│   │               ├── model/
│   │               │   ├── User.java
│   │               │   └── BlogPost.java
│   │               ├── repository/
│   │               │   ├── UserRepository.java
│   │               │   └── BlogPostRepository.java
│   │               └── service/
│   │                   ├── UserService.java
│   │                   └── BlogPostService.java
│   └── resources/
│       ├── db/migration/
│       │   ├── V1__Create_users_table.sql
│       │   └── V3__Create_blog_posts_table.sql
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── example/
                └── demo/
                    └── service/
                        └── BlogPostServiceTest.java
