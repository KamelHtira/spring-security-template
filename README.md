# Spring Boot REST API Project

A RESTful API built with Spring Boot 3, featuring JWT authentication, PostgreSQL integration, and comprehensive API documentation using Swagger/OpenAPI.
The main goal is to save you 2 to 3 hours of setup time by providing a ready-to-use Spring Security, Swagger, and JWT-integrated database configuration. Simply download this and jump straight into your project specifications! 🚀

## Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Security 6
- PostgreSQL
- JWT Authentication
- Swagger/OpenAPI Documentation
- Maven

## Prerequisites

- JDK 21 or higher
- PostgreSQL 12 or higher
- Maven 3.8+

## Project Structure
```
src
├── main
│   ├── java
│   │   └── com
│   │       └── SpringBootApplicationTemplate
│   │           └── demo
│   │               ├── DTOs
│   │               │   ├── Request.java
│   │               │   ├── Response.java
│   │               │   └── templates
│   │               │       ├── AuthenticationRequestDTO.java
│   │               │       ├── AuthenticationResponseDTO.java
│   │               │       ├── RegisterRequestDTO.java
│   │               │       └── RegisterResponseDTO.java
│   │               ├── SpringBootApplicationTemplate.java
│   │               ├── config
│   │               │   ├── ApplicationConfig.java
│   │               │   ├── JwtAuthenticationFilter.java
│   │               │   ├── RequestLoggingFilter.java
│   │               │   ├── SecurityConfig.java
│   │               │   └── SwaggerConfig.java
│   │               ├── controller
│   │               │   ├── AuthenticationController.java
│   │               │   └── HealthCheckController.java
│   │               ├── model
│   │               │   ├── Role.java
│   │               │   └── User.java
│   │               ├── repository
│   │               │   └── UserRepository.java
│   │               └── service
│   │                   ├── AuthenticationService.java
│   │                   └── JwtService.java
│   └── resources
│       ├── application.properties
│       └── logback.xml
```

## Project Setup

1. Clone the repository:
```bash
git clone https://github.com/yourusername/project-name.git
cd project-name
```

2. Configure PostgreSQL database properties in `src/main/resources/application.yml`:
```yaml
spring.application.name=SpringBootApplicationTemplate

# Database
spring.datasource.url=jdbc:postgresql://localhost:5433/db_name
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate settings (Optional)
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=create-drop

#Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

```

## Building and Running

1. Build the project:
```bash
mvn clean install
```

2. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Swagger UI is available at: `http://localhost:8080/swagger-ui/index.html`
OpenAPI documentation: `http://localhost:8080/api-docs`

## Authentication

The API uses JWT Bearer token authentication. To access protected endpoints:

1. Obtain JWT token by authenticating at `/api/auth/authenticate`
2. Include the token in the Authorization header:
```
Authorization: Bearer your_jwt_token
```

## API Endpoints

### Authentication
- POST `/api/auth/register` - Register new user
- POST `/api/auth/authenticate` - Authenticate user and get token

### Protected Endpoints
- POST `/api/auth/protected` - Validate token (Authenticated only)

### Authentication
- GET `/ping` - Health check
  
## Security

- BCrypt password encoding
- JWT token authentication
- Role-based access control
- Spring Security 6 implementation

## Database Schema

The application uses PostgreSQL with the following main entities:

- Users
- Roles
- Other domain-specific entities

## Error Handling

The API implements a global exception handling mechanism with appropriate HTTP status codes and error messages.

## Testing

Run tests using:
```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License

## Support

For support, email kamel2htira@gmail.com or open an issue in the repository.
