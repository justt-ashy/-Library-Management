# 📚 Library Management System (Backend)

A powerful backend system built with **Spring Boot** to manage library operations efficiently.
It supports secure authentication, role-based access, and smooth handling of books and users.

---

## 🚀 Features

* 🔐 Role-Based Authentication (Admin 👑 / User 👤)
* 🔑 Secure Login using JWT
* 📖 Book Management (Add, Update, Delete, View)
* 👥 User Management
* 🔄 Issue & Return Books
* 🔍 Search Books by Title/Author
* ⚠️ Proper Validation & Error Handling

---

## 🛠️ Tech Stack

* 💻 **Backend:** Java, Spring Boot
* 🛡️ **Security:** Spring Security, JWT
* 🗄️ **Database:** MySQL
* 🔗 **ORM:** Hibernate / JPA
* ⚙️ **Build Tool:** Maven
* 🧪 **Testing Tools:** Postman, IntelliJ IDEA

---

## 📂 Project Structure

* 📦 `controller` → Handles API requests
* 🧠 `service` → Business logic
* 🗃️ `repository` → Database operations
* 🧾 `model/entity` → Data models
* 🔐 `security` → Authentication & JWT config

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

git clone https://github.com/justt-ashy/-Library-Management.git

### 2️⃣ Open in IntelliJ

Import as a Maven project and let dependencies download.

### 3️⃣ Configure Database

Update `application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password

### 4️⃣ Run the Application

mvn spring-boot:run

---

## 🌐 API Endpoints

### 🔑 Auth

* POST `/auth/register` → Register new user
* POST `/auth/login` → Login & get JWT

### 📚 Books

* GET `/books` → Get all books
* POST `/books` → Add book
* PUT `/books/{id}` → Update book
* DELETE `/books/{id}` → Delete book

### 👥 Users

* GET `/users` → Get users
* POST `/users` → Add user

### 🔄 Transactions

* POST `/issue` → Issue book
* POST `/return` → Return book

---

## 🧠 Key Concepts Used

* REST API Design 🌐
* Layered Architecture 🏗️
* Role-Based Access Control 🔐
* JWT Authentication 🔑
* Entity Relationships (OneToMany, ManyToOne) 🔗

---

## 🚧 Future Improvements

* 🌐 Add Frontend (React / Angular)
* 📊 Pagination & Sorting
* 🐳 Docker Support
* 📧 Email Notifications

---

## 👨‍💻 Author

**Aashita Bansal**
🎓 B.Tech CSE | 💻 Backend Developer | ☕ Java Enthusiast

---

⭐ If you found this project helpful, consider giving it a star!
