package com.group4.bankSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // form POST'ları için CSRF engelini kaldır
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**", "/customer/**").permitAll() // kendi URL'lerini burada tanımla
                .anyRequest().permitAll() // tüm diğer istekleri de serbest bırak
            )
            .formLogin(form -> form.disable()) // Spring'in default login formunu kapat
            .httpBasic(httpBasic -> httpBasic.disable()); // Basic Auth'u da kapat (gerekmiyorsa)

        return http.build();
    }
}
