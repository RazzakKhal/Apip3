package com.applicationtrain.applicationtrain;

import com.applicationtrain.applicationtrain.service.AccueilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private AccueilServiceImpl accueilService;

    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

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


        // j'ajoute le filtre que j'ai créé jwtAuthenticationFilter à la liste des filtres de spring, je lui dis de le lancer
        // avant son filtre UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // pour pouvoir faire l'injection de dépendance de BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider()));
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(accueilService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}