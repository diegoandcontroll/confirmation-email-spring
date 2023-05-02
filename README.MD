# Spring Boot Security with Sender Mail Implementation
This project demonstrates the implementation registration and confirmation email sender!

## Features
* User registration and send email to confirmation login
* Password encryption using BCrypt
* Role-based authorization with Spring Security
* Customized access denied handling
* Send email to user 

## Technologies
* Spring Boot 
* Spring Security
* BCrypt
* Java mail sender
* Maven
 
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 11+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/diegoandcontroll/confirmation-email-spring.git`
* Navigate to the project directory: cd spring-boot-security-jwt
* Add database "jwt_security" to postgres 
* Build the project: mvn clean install
* Run SMTP Server with docker run -p 1080:1080 -p 1025:1025 maildev/maildev
* Run the project: docker-compose up -d && mvn spring-boot:run 

-> The application will be available at http://localhost:8080.
