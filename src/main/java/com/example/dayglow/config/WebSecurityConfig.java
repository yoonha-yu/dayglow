package com.example.dayglow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Postman 테스트용, 나중에 필요하면 켜기
                .authorizeHttpRequests()
                .requestMatchers("/api/users/signup", "/api/users/login", "/api/users/find-id", "/api/users/reset-password").permitAll() // 인증 없이 접근 허용
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // 기본 인증 (JWT 있으면 나중에 제거)
        return http.build();
    }
}
