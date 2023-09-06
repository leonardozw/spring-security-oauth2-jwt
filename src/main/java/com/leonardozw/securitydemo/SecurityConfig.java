package com.leonardozw.securitydemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(authorizeConfig -> {
                authorizeConfig
                    .requestMatchers("/public").permitAll()
                    .requestMatchers("/logout").permitAll()
                    .anyRequest().authenticated();
            })
            .oauth2Login(Customizer.withDefaults())
            .oauth2ResourceServer(resourceServer -> {
                resourceServer.jwt();
            });

            return http.build();

        // outdated version:
        // return http
        //     .authorizeHttpRequests(
        //         authorizeConfig -> {
        //             authorizeConfig.requestMatchers("/public").permitAll();
        //             authorizeConfig.requestMatchers("/logout").permitAll();
        //             authorizeConfig.anyRequest().authenticated();
        //         })
        //     .oauth2Login(Customizer.withDefaults())
        //     .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        //     .build();

    }
}
