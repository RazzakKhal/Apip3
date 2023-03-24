package com.applicationtrain.applicationtrain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // desactive l'obligation d'envoyer un jeton à chaque requête
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/accueil/inscription", "/accueil/connexion").permitAll()
                        .anyRequest().authenticated()
                )

                .httpBasic(withDefaults()) // définit la facon de s'authentifier

                .cors();// ajoute une en-tete CORS aux réponses (sans quoi erreur)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // cela signifie que l'on n'utilisera pas de session pour stocker des infos coté back


        return http.build();
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // pour pouvoir faire l'injection de dépendance de BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }

}