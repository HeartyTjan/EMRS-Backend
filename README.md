
#EMRS Backend

The EMRS Backend is a Java-based REST API that handles core functionalities for an electronic medical records system. It allows hospitals and clinics to manage patient data, doctor appointments, prescriptions, and medical history.


## Table of Contents

- About the Project
- Tech Stack
- Features
- Installation
- Environment Variables
- Usage
- API Endpoints

## Tech Stack

- Language: Java (Spring Boot)
- Database: MySQL
- Authentication: JWT (JSON Web Token)
- Frameworks & Libraries: Spring Security, Hibernate, Lombok



## Features

- Secure user authentication & role-based authorization (Doctor, Patient)
- Patient record management (CRUD operations)
- Appointment scheduling and tracking
- Medical history storage
 


## Installation

 1. Clone the Repository

```bash
 git clone https://github.com/HeartyTjan/EMRS-Backend.git
  cd emrs-backend
```
 2. 2. Set Up the Database
Ensure you have PostgreSQL or MySQL installed. Create a new database:
```bash
  CREATE DATABASE emrs_db;
```
3. 	Update application.properties with your DB credentials:
```bash
	spring.datasource.url=jdbc:mysql://localhost:3306/emrs_db
	spring.datasource.username=root
	spring.datasource.password=your_password
	spring.jpa.hibernate.ddl-auto=update
	spring.jpa.show-sql=true
```
4. Install Dependencies & Run the Application
```bash
# If using Maven
mvn install
mvn spring-boot:run

```
## Usage

Once running, access the API at:

```bash
http://localhost:8080/api
```
## Postman Documentation for API Endpoints

[API Reference](https://rb.gy/x3drcv)

