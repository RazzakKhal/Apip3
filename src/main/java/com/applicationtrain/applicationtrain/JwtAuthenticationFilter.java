package com.applicationtrain.applicationtrain;

import com.applicationtrain.applicationtrain.service.AccueilServiceImpl;
import com.applicationtrain.applicationtrain.service.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//La classe JwtAuthenticationFilter étend la classe OncePerRequestFilter de Spring Security,
// qui est un filtre personnalisé exécuté une seule fois pour chaque requête HTTP.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    private final AccueilServiceImpl accueilService;



    // injection de dépendance pour avoir une instance dans mes propriétés jwtTokenProvider et accueilService
    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, AccueilServiceImpl accueilService) {
        this.jwtTokenProvider = tokenProvider;
        this.accueilService = accueilService;
    }

    // methode qui me permet de récupérer le token stocker dans le header Authorization
    // depuis la requete émise par mon appli angular
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    //methode qui récupére les informations du token puis vas utiliser ma méthode loadUserByUsername de mon accueilService
    // pour définir l'utilisateur authentifié
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);

        if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
            String username = jwtTokenProvider.getUsernameFromJwt(jwt);
            UserDetails userDetails = accueilService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


}

