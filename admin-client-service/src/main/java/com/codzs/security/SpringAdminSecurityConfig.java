package com.codzs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringAdminSecurityConfig {
    @Bean
    public SecurityFilterChain adminServiceFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/management/**").permitAll()
                .requestMatchers("/actuator/**").hasRole("MANAGEMENT_ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();

        return http.build();
    }
}
