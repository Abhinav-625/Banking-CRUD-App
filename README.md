# Full Stack Banking CRUD App Using Thymeleaf

A Spring Boot banking web application built with Thymeleaf and MySQL.  
Supports basic account operations like create, view, deposit, withdraw, and delete.

## Tech Stack
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven

## Features
- Create account
- View account by email
- Deposit money
- Withdraw money
- Delete account
- View all accounts
- Custom exception handling
- Clean MVC architecture

## Project Structure
```

src/main/java/com/example/bankingDemo
├─ controller
├─ service
├─ serviceImpl
├─ repository
├─ entity
├─ dto
├─ mapper
└─ exception

src/main/resources
├─ templates
├─ static/css
└─ application.properties

````

## Run Locally

### 1. Create database
```sql
create database banking;
````

### 2. Configure DB

```
src/main/resources/application.properties
```

```
spring.datasource.url=jdbc:mysql://localhost:3306/bankingDemo
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run app

```
mvnw.cmd spring-boot:run
```

App runs at:

```
http://localhost:8080
```

## Author

Abhinav Tomar
