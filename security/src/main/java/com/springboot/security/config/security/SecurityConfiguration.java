package com.springboot.security.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URLS = {
        "/sign-api/sign-in",
        "/sign-api/sign-up",
        "/sign-api/exception",
        "/v3/api-docs/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/webjars/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, JwtTokenProvider jwtTokenProvider) throws Exception {
        httpSecurity.httpBasic(HttpBasicConfigurer::disable).csrf(CsrfConfigurer::disable);
        httpSecurity.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(WHITE_LIST_URLS).permitAll()
            .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
            .anyRequest().hasRole("ADMIN"));
        httpSecurity.exceptionHandling(authenticationManager -> authenticationManager
            .accessDeniedHandler(new CustomAccessDeniedHandler())
            .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), 
            UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
