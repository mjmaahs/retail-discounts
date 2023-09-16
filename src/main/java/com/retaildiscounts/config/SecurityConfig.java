package com.retaildiscounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.POST, "/api/calculate-net-amount").hasRole("CASHIER")
                        .requestMatchers(HttpMethod.GET, "/actuator/*").hasRole("CASHIER")
        );
        http.httpBasic(Customizer.withDefaults());
        http.userDetailsService(userDetailsService);
        //http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
