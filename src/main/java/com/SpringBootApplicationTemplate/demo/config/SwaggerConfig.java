package com.SpringBootApplicationTemplate.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "SpringBoot, Spring Security and PostgreSQL Template",
                version = "1.0",
                description = "Your API Description",
                contact = @Contact(
                        name = "Kamel Htira",
                        email = "kamel2htira@gmail.com"
                )
        ),
        servers = @Server(url = "http://localhost:8080")
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}

