# Task Management System

## Project Overview

The Task Management System is a web application designed to help users manage their tasks efficiently. Users can register and log in to the system to create, view, edit, complete, and delete tasks. The application also allows users to view archived and deleted tasks, as well as track recent activity through an activity log. User profiles can be updated, and the application is secured with session-based authentication.

## Features

- **User Registration & Login:** Secure user registration and login functionalities.
- **Task Management:** Add, view, edit, complete, delete, and categorize tasks.
- **Archived Tasks:** Automatically archive completed tasks and manage archived tasks.
- **Deleted Tasks:** Soft delete tasks and manage deleted tasks.
- **Activity Log:** Track recent activities performed by the user.
- **User Profile Management:** View and update user profile information.
- **Logout:** End user sessions securely.

## Tech Stack

- **Java:** Backend logic implemented using Java.
- **Jakarta Servlet:** Handles HTTP requests and manages session data.
- **JSP (JavaServer Pages):** Frontend rendered dynamically using JSP.
- **JDBC (Java Database Connectivity):** Database interactions managed via JDBC.
- **PostgreSQL:** Relational database for persistent data storage.
- **HTML & CSS:** User interface styled with CSS and structured using HTML.
- **Dependencies:** Jakarta Servlet API, PostgreSQL JDBC Driver
- **Web Server:** Apache Tomcat

## Database Setup

### Database Configuration
The application uses PostgreSQL as the database. Below are the database details:

- **Database Name:** taskmanager
- **Username:** postgres
- **Password:** pineapples
- **Driver Class Name:** `org.postgresql.Driver`

### SQL Schema
Use the following SQL commands to create the necessary tables for the application.

```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    title VARCHAR(255) NOT NULL,
    due_date DATE NOT NULL,
    category VARCHAR(50),
    priority VARCHAR(20),
    status VARCHAR(20) DEFAULT 'active'
);

CREATE TABLE activity_log (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    action VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
