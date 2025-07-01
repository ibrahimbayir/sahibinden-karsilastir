# sahibindenkarsilastir.com - Backend

This is the backend service of the **sahibindenkarsilastir.com** project – a web platform to list and compare second-hand cars.

Built with **Java Spring Boot**, it provides a RESTful API that manages users, car listings, authentication, and car comparisons.

## 🚗 Project Overview

The backend is responsible for:
- Managing user accounts (buyers, sellers, admins)
- Creating, updating, deleting car listings
- Filtering and comparing cars based on price, mileage, model, etc.
- Providing secure login & registration with Spring Security
- Storing data in a structured MySQL database

## 🧰 Technologies Used

- **Java** & **Spring Boot**
- **Spring Security** for authentication
- **Spring Data JPA (Hibernate)** for ORM
- **MySQL** as the relational database
- **Lombok** for boilerplate reduction
- **RESTful API** with JSON responses
- **Postman / Swagger** for API testing

## 📂 Project Structure

```
src/
├── controller/
├── service/
├── model/
├── repository/
├── config/
└── dto/
```

## 🛠️ Setup & Installation

1. **Clone the repo**
```bash
git clone https://github.com/<username>/sahibindenkarsilastir-backend.git
cd sahibindenkarsilastir-backend
```

2. **Create a MySQL database**
```sql
CREATE DATABASE sahibindenkarsilastir;
```

3. **Configure application.properties**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sahibindenkarsilastir
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

4. **Run the application**
```bash
./mvnw spring-boot:run
```

## 📮 API Endpoints

| Method | Endpoint                | Description                 |
|--------|-------------------------|-----------------------------|
| POST   | /api/auth/register      | Register new user           |
| POST   | /api/auth/login         | Authenticate user           |
| GET    | /api/cars               | List all cars               |
| POST   | /api/cars               | Create a new listing        |
| PUT    | /api/cars/{id}          | Update a listing            |
| DELETE | /api/cars/{id}          | Delete a listing            |
| GET    | /api/cars/compare       | Compare multiple cars       |

## 🧪 Testing

- You can use **Postman** or **Swagger UI** (if enabled) to test the REST endpoints.

---

## 👥 Authors

- İbrahim Bayır  
- Mehmet Çavdar  
- Muhammet İyidil
