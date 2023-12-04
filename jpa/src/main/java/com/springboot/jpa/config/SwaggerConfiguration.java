package com.springboot.jpa.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Spring Boot Practice: JPA", description = "Spring Boot Practice: JPA"))
public class SwaggerConfiguration {
}
