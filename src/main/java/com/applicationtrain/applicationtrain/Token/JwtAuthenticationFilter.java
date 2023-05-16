package com.applicationtrain.applicationtrain.Token;

import com.applicationtrain.applicationtrain.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//La classe JwtAuthenticationFilter étend la classe OncePerRequestFilter de Spring Security,
// qui est un filtre personnalisé exécuté une seule fois pour chaque requête HTTP.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
     @Autowired
     private UserRepository userRepository;
     @Autowired
     private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String userMail = null;
        String jwt = null;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
        }else{
            jwt = authHeader.substring(7);
            userMail= jwtUtil.extractUsername(jwt);
        }

        if(userMail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userRepository.findByMail(userMail);
            if(jwtUtil.validateToken(jwt, userDetails)){

                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            }
            filterChain.doFilter(request, response);
        }
    }
}

