package com.example.LMS_Backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(com.example.LMS_Backend.repository.UserRepository userRepository) {
        return new com.example.LMS_Backend.security.UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public RequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**", "/auth/signup", "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/book/**", "/categories/**", "/issued-books/**", "/users/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/book/**", "/categories/**", "/issued-books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/book/**", "/categories/**", "/issued-books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/book/**", "/categories/**", "/issued-books/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(requestLoggingFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtUtil jwtUtil(@Value("${security.jwt.secret}") String base64Secret,
                           @Value("${security.jwt.expiration-ms:3600000}") long expirationMs) {
        return new JwtUtil(base64Secret, expirationMs);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService uds) {
        return new JwtAuthenticationFilter(jwtUtil, uds);
    }

    @Bean
    public org.springframework.security.authentication.AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
