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

## Project Structure

```plaintext
TaskManager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── taskmanager/
│   │   │   │   │   ├── controller/
│   │   │   │   │   │   ├── RegisterServlet.java
│   │   │   │   │   │   ├── LoginServlet.java
│   │   │   │   │   │   ├── LogoutServlet.java
│   │   │   │   │   │   ├── TaskServlet.java
│   │   │   │   │   │   ├── ProfileServlet.java
│   │   │   │   │   │   ├── ArchiveServlet.java
│   │   │   │   │   │   ├── DeletedTasksServlet.java
│   │   │   │   │   │   ├── ActivityLogServlet.java
│   │   │   │   │   ├── dao/
│   │   │   │   │   │   ├── UserDAO.java
│   │   │   │   │   │   ├── TaskDAO.java
│   │   │   │   │   │   ├── ActivityLogDAO.java
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── User.java
│   │   │   │   │   │   ├── Task.java
│   │   │   │   │   │   ├── ActivityLog.java
│   │   │           └── util/
│   │   │               └── DBConnection.java
│   ├── webapp/
│   │   ├── WEB-INF/
│   │   │   ├── web.xml
│   │   ├── jsp/
│   │   │   ├── register.jsp
│   │   │   ├── login.jsp
│   │   │   ├── tasks.jsp
│   │   │   ├── profile.jsp
│   │   │   ├── archived_tasks.jsp
│   │   │   ├── deleted_tasks.jsp
│   │   │   ├── activity_log.jsp
│   │   ├── css/
│   │   │   ├── style.css
│   │   ├── index.jsp


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
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    due_date DATE NOT NULL,
    category VARCHAR(255) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    deleted BOOLEAN DEFAULT FALSE,
    user_id INTEGER NOT NULL REFERENCES users(id)
);

CREATE TABLE activity_log (
    id SERIAL PRIMARY KEY,
    action VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL REFERENCES users(id)
);
