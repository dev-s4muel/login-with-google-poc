package com.poc.login_with_google_poc;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

@Configuration
public class SpringConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
        return http
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/", "/login", "/start-google-login").permitAll();
                    registry.anyRequest().authenticated();
                })
                .oauth2Login(oauth2login -> oauth2login
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            // Obtém os dados do usuário autenticado
                            String email = "email";
                            String picture = "picture";
                            String name = "name";

                            // Cria um JWT com os mesmos atributos dos demais endpoints
                            Map<String, Object> claims = Map.of(
                                    "kind", "USER",
                                    "nickName", name,
                                    "active", true,
                                    "accepted", true,
                                    "picture", picture,
                                    "status", "ACTIVE"
                            );
                            String token = jwtUtil.generateToken(email, claims.toString());

                            // Define a resposta JSON
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");

                            String jsonResponse = String.format("{\"token\": \"%s\"}", token);
                            response.getWriter().write(jsonResponse);
                        }))
                .build();
    }
}

