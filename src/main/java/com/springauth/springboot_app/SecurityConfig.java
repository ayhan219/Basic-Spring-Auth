package com.springauth.springboot_app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF korumasını devre dışı bırakıyoruz (geliştirme için)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Tüm endpointlere şifresiz erişim
                );
        return http.build();
    }
}
