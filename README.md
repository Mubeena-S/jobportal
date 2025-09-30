# Job Portal System

🚀 A complete Job Portal System built with **Java, Spring Boot, Hibernate, JPA, MySQL, and REST APIs**.  
This project allows employers to post jobs and job seekers to search and apply for jobs in a secure and efficient way.  

---

## 📌 Features
- 🔐 **User Authentication** with JWT (Login & Registration)  
- 👤 **Job Seekers** can search and apply for jobs  
- 🏢 **Employers** can create and manage job postings  
- 🛠 **Admin Dashboard** for monitoring  
- 📊 **Database Integration** with MySQL  
- 🌐 **RESTful APIs** tested with Postman  

---

## 🛠 Tech Stack
- **Backend:** Java 17, Spring Boot, Hibernate, JPA  
- **Database:** MySQL  
- **APIs:** REST APIs  
- **Tools:** Git, Postman, Maven  

---

## 📂 Project Structure
JobPortal-System/
│
├── src/main/java/com/example/jobportal/ # Backend code
├── src/main/resources/ # Config files
│ └── application.properties # DB Configurations
├── src/test/java/com/example/jobportal/ # Test cases
├── pom.xml # Maven dependencies
└── README.md # Project Documentation


---

## 🚀 Getting Started

### Prerequisites
- Install **Java 17+**
- Install **Maven**
- Install **MySQL**
- Install **Postman** (for API testing)

### Steps to Run
1. Clone the repository  
   ```bash
   git clone https://github.com/Mubeena-S/Job-Portal-System.git
2. Import project into Spring Tool Suite (STS) or IntelliJ IDEA
3. Configure application.properties with your MySQL username & password
4. Run the application
   mvn spring-boot:run
5. APIs will be available at:
http://localhost:8086/

🧪 API Testing with Postman

Register User → /api/auth/register

Login User → /api/auth/login

Get All Jobs → /api/jobs

Post Job (Employer) → /api/jobs/create

Apply for Job → /api/jobs/apply/{jobId}

👩‍💻 Author

Mubeena Savalagi

💼 LinkedIn: www.linkedin.com/in/mubeena-savalagi-0490a91b7

📧 Email: savalagimubeena@gmail.com
