package main.java.com.springboot.actuator.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Spring Boot Practice: Actuator", description = "Spring Boot Practice: Actuator"))
public class SwaggerConfiguration {
}
