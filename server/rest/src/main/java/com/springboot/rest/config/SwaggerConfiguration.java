package com.springboot.rest.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Spring Boot Practice: REST", description = "Spring Boot Practice: REST"))
public class SwaggerConfiguration {
}
