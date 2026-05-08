# PlaceTrack – Student Placement Tracking System

## Overview

PlaceTrack is a backend-based Student Placement Tracking System developed using Spring Boot and MySQL. The project helps students manage placement-related information such as profiles, job applications, and interview notes through REST APIs.

This project follows layered backend architecture using Controllers, Services, Repositories, DTOs, and Entity Models.

---

# Features

## User Management

* Create users
* Fetch user details
* Update user information
* Delete users

## Student Profile Management

* Store CGPA, skills, branch, college, resume link
* Track placement status
* Link profile to user

## Job Application Tracking

* Apply to companies
* Track role and application status
* View all applications
* Filter applications by student and status

## Notes System

* Add personal preparation/interview notes
* Store multiple notes for a user
* Retrieve all notes
* Update or delete notes

## Dashboard API

Single API to fetch:

* User details
* Student profile
* Job applications
* Notes

---

# Tech Stack

| Technology      | Purpose                   |
| --------------- | ------------------------- |
| Java            | Core programming language |
| Spring Boot     | Backend framework         |
| Spring Data JPA | Database operations       |
| Hibernate       | ORM framework             |
| MySQL           | Relational database       |
| Maven           | Dependency management     |
| Postman         | API testing               |
| Git & GitHub    | Version control           |

---

# Architecture

```text
Client/Postman
       ↓
Controller Layer
       ↓
Service Layer
       ↓
Repository Layer
       ↓
MySQL Database
```

---

# Project Structure

```text
src/main/java/com/placetrack/placetrack
│
├── controller
├── service
├── repository
├── model
├── dto
└── PlacetrackApplication.java
```

---

# Database Tables

## users

Stores user information.

## student_profiles

Stores academic and placement profile details.

## job_applications

Stores applied companies and statuses.

## notes

Stores personal preparation/interview notes.

---

# Entity Relationships

* One User → One Student Profile
* One User → Many Job Applications
* One User → Many Notes

---

# API Endpoints

## User APIs

| Method | Endpoint          | Description    |
| ------ | ----------------- | -------------- |
| POST   | `/api/users`      | Create user    |
| GET    | `/api/users`      | Get all users  |
| GET    | `/api/users/{id}` | Get user by ID |
| PUT    | `/api/users/{id}` | Update user    |
| DELETE | `/api/users/{id}` | Delete user    |

---

## Student Profile APIs

| Method | Endpoint             | Description       |
| ------ | -------------------- | ----------------- |
| POST   | `/api/profiles`      | Create profile    |
| GET    | `/api/profiles`      | Get all profiles  |
| GET    | `/api/profiles/{id}` | Get profile by ID |
| PUT    | `/api/profiles/{id}` | Update profile    |
| DELETE | `/api/profiles/{id}` | Delete profile    |

---

## Job Application APIs

| Method | Endpoint                             | Description              |
| ------ | ------------------------------------ | ------------------------ |
| POST   | `/api/applications`                  | Create application       |
| GET    | `/api/applications`                  | Get all applications     |
| GET    | `/api/applications/{id}`             | Get application by ID    |
| GET    | `/api/applications/student/{userId}` | Get applications by user |
| GET    | `/api/applications/status/{status}`  | Filter by status         |
| PUT    | `/api/applications/{id}`             | Update application       |
| DELETE | `/api/applications/{id}`             | Delete application       |

---

## Notes APIs

| Method | Endpoint                   | Description       |
| ------ | -------------------------- | ----------------- |
| POST   | `/api/notes`               | Add note          |
| GET    | `/api/notes`               | Get all notes     |
| GET    | `/api/notes/{id}`          | Get note by ID    |
| GET    | `/api/notes/user/{userId}` | Get notes by user |
| PUT    | `/api/notes/{id}`          | Update note       |
| DELETE | `/api/notes/{id}`          | Delete note       |

---

## Dashboard API

| Method | Endpoint                   | Description                 |
| ------ | -------------------------- | --------------------------- |
| GET    | `/api/dashboard/user/{id}` | Get complete dashboard data |

---

# Sample JSON Request

## Create User

```json
{
  "name": "Deepika",
  "email": "deepika@gmail.com",
  "password": "deepika123"
}
```

---

## Create Student Profile

```json
{
  "user": {
    "id": 1
  },
  "cgpa": 8.7,
  "skills": "Java, Spring Boot, SQL",
  "resumeLink": "https://resume-link.com",
  "college": "ABC College",
  "branch": "CSE",
  "placementStatus": "PLACED"
}
```

---

## Create Job Application

```json
{
  "user": {
    "id": 1
  },
  "companyName": "Google",
  "role": "SDE",
  "appliedDate": "2026-05-08",
  "status": "APPLIED"
}
```

---

## Create Note

```json
{
  "content": "Need to revise DBMS",
  "user": {
    "id": 1
  }
}
```

---

# Sample Dashboard Response

```json
{
  "user": {
    "id": 1,
    "name": "Deepika",
    "email": "deepika@gmail.com"
  },
  "profile": {
    "cgpa": 8.7,
    "skills": "Java, Spring Boot, SQL"
  },
  "applications": [
    {
      "companyName": "Google",
      "role": "SDE",
      "status": "APPLIED"
    }
  ],
  "notes": [
    {
      "content": "Need to revise DBMS"
    }
  ]
}
```

---

# How to Run the Project

## 1. Clone Repository

```bash
git clone https://github.com/DeepikaPeddi/placetrack.git
```

## 2. Open in IntelliJ IDEA

Open the project folder.

---

## 3. Configure MySQL

Create database:

```sql
CREATE DATABASE placetrack_db;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/placetrack_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

---

## 4. Run Spring Boot Application

Run:

```text
PlacetrackApplication.java
```

Server starts at:

```text
http://localhost:8080
```

---

## 5. Test APIs

Use Postman to test REST APIs.

---

# Future Improvements

* Spring Security + JWT Authentication
* Role-based access
* React frontend
* Resume file upload
* Email notifications
* Swagger API documentation
* Deployment on Render/Railway

---

# Learning Outcomes

This project helped in understanding:

* REST API development
* Spring Boot architecture
* JPA & Hibernate relationships
* DTO usage
* CRUD operations
* MySQL integration
* Git & GitHub workflow
* API testing using Postman

---

# Author

Deepika Peddi

GitHub:
[https://github.com/DeepikaPeddi](https://github.com/DeepikaPeddi)
