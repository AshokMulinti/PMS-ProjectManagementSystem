package com.ashok.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/register", "/login", "/access-denied").permitAll()
                        // Admin-only endpoints
                        .requestMatchers(
                                "/addProject", "/saveProject", "/editProject/**", "/updateProject", "/deleteProject/**",
                                "/addEmployee", "/saveEmployee", "/editEmployee/**", "/updateEmployee", "/deleteEmployee/**",
                                "/addTask", "/saveTask", "/editTask/**", "/updateTask", "/deleteTask/**"
                        ).hasRole("ADMIN")
                        // Accessible to all authenticated users
                        .requestMatchers("/getAllProjects", "/getAllTasks", "/getAllEmployees", "/home").authenticated()
                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .usernameParameter("email")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied") // Redirect to custom access-denied page
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}