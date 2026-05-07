# 🗂️ Task Manager System (Spring Boot)

A full-stack Task Management System developed using Spring Boot, designed to efficiently manage projects, tasks, and team collaboration.

This system includes secure authentication, project management, task tracking, dashboard analytics, and team collaboration features.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- Secure login and registration system  
- Role-based access management  
- Password encryption using BCrypt  
- Spring Security integration  
- Session-based authentication  

---

### 📁 Project Management
- Create and manage projects  
- Assign team members to projects  
- Track project progress  
- View all user-related projects  
- Dashboard overview for projects  

---

### ✅ Task Management
- Create tasks within projects  
- Update task status  
- Assign tasks to team members  
- Track completed and pending tasks  
- Manage deadlines and priorities  

---

### 👥 Team Collaboration
- Add members to projects  
- Shared project access  
- Collaborative task handling  
- User-specific project visibility  

---

### 📊 Dashboard
- Project statistics overview  
- Pending and completed task counts  
- User project tracking  
- Organized task management UI  

---

### 🗄️ Database Integration
- MySQL database support  
- JPA & Hibernate ORM  
- Entity relationship mapping  
- Repository-based data access  

---

## 🧰 Tech Stack

| Layer      | Technology |
|------------|------------|
| Backend    | Spring Boot |
| Security   | Spring Security |
| Database   | MySQL (JPA/Hibernate) |
| Frontend   | Thymeleaf, HTML, CSS, Bootstrap |
| Build Tool | Maven |

---

## 📂 Project Structure

```text
src/
 ├── controller/
 ├── service/
 ├── dao/
 ├── entity/
 ├── security/
 ├── templates/
 └── static/
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/NAVEEN170804/Task-Manager.git
```

---

### 2️⃣ Create Database

```sql
CREATE DATABASE taskmanager;
```

---

### 3️⃣ Configure application.properties

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=${PORT:8080}
```

---

### 4️⃣ Run the Project

```bash
mvn spring-boot:run
```

---

### 5️⃣ Open in Browser

```text
http://localhost:8080
```

---

## 🌍 Deployment

- Backend Hosted on Render  
- MySQL Database Hosted on Railway  

---

## 📸 Screenshots

### 🔐 Login Page
<img width="1917" height="1022" alt="Login Page" src="https://github.com/user-attachments/assets/8b54baa9-7533-41ae-a34a-6de11ef9c85c" />

<br><br>

### 📝 Signup Page
<img width="1917" height="1022" alt="Signup Page" src="https://github.com/user-attachments/assets/14b2e951-6e9b-4588-85f2-4bea657c8b74" />

<br><br>

### 👨‍💼 Admin Dashboard
<img width="1917" height="1022" alt="Admin-dashboard" src="https://github.com/user-attachments/assets/fdfe13ab-f111-49d7-ad0a-54807c394256" />

<br><br>

### 📁 Create Project
<img width="1917" height="1022" alt="Create Project" src="https://github.com/user-attachments/assets/9a749486-2e7c-4edb-a372-824b9e7fb0d2" />

<br><br>

### ✅ Create Task
<img width="1917" height="1022" alt="Create Task" src="https://github.com/user-attachments/assets/ed40c5c9-93a2-4861-a31d-99493288e28c" />

<br><br>

### 👥 Member Dashboard
<img width="1917" height="1022" alt="Member dashboard" src="https://github.com/user-attachments/assets/195edd03-b988-47e0-972b-d8ffc44894c2" />

<br><br>

### 📋 Member Task
<img width="1917" height="1022" alt="Member Task" src="https://github.com/user-attachments/assets/720f7717-ccdd-4275-98ae-fcf645d85d24" />

---

## 🔥 Key Highlights

- Secure authentication system  
- Team-based project collaboration  
- Task assignment and tracking  
- Dashboard analytics  
- Clean MVC architecture  
- Cloud deployment ready  

---

## 📌 Future Enhancements

- Email notifications  
- File upload support  
- REST API integration  
- JWT authentication  
- Docker deployment  
- Real-time notifications  

---

## 👨‍💻 Author

**Naveen A**  

📧 naveena170804@gmail.com  
🐙 https://github.com/NAVEEN170804  
🌐 https://naveen170804.github.io/Portfolio  

---

⭐ If you like this project, give it a star on GitHub!
