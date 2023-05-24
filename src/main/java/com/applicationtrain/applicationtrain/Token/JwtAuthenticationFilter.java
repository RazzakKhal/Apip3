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
    // j'etends OncePerRequestFilter car mon filtre devra se lancer à chaque requete


     @Autowired
     private UserRepository userRepository;   // je récupère une instance d'une classe userRepo qui gère le CRUD de la table user
     @Autowired
     private JwtUtil jwtUtil; // je recupere une instance de jwtUtil qui gère generation, validation ... du token


    // j'implémente la méthode doFilterInternal qui gère les actions d'un filtre
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            // en paramètre c'est la requête récupéré du front (via fetch), la reponse que je peux renvoyer, et la chaine de filtre
    // de securité de spring
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        // je récupère le header Authorization de la requete (requete envoyée par le front avec fetch)
        // j'instancie 2 variable dont je compte me service pour stocker mail et token
        String userMail = null;
        String jwt = null;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            // si il n'y a pas de header Authorization ou que celui-ci ne commence pas par "Bearer" on passe au filtre suivant
            filterChain.doFilter(request, response);
        }else{
            // sinon je supprime les 7premieres lettre du contenu du header Authorization, cela aura pour effet
            // de supprimer "Bearer " et de ne laisser que le token qui sera stocké dans la variable jwt
            jwt = authHeader.substring(7);

            // gràce a mon instance de la clase jwtUtil j'extrait le username utilisé par l'utilisateur pour se connecter
            // (le mien se connecte via le mail donc il s'agit de son mail)
            userMail= jwtUtil.extractUsername(jwt);
        }

        if(userMail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // si j'ai pu récupérer son mail et que dans le context il n'y a rien (si rien dans le contexte c'est que spring
            // considère que personne n'est authentifié)
            // ALORS je récupère l'utilisateur en base de données par son mail et je le stock dans une variable de type
            // UserDetails
            UserDetails userDetails = userRepository.findByMail(userMail);
            if(jwtUtil.validateToken(jwt, userDetails)){ // si mon token est valide au vu des informations de l'utilisation récupéré
                // ALORS je peux ajouter un utilisateur dans le contexte de spring, il considèrera alors qu'on est authentifié
                // je le met sous forme de UsernamePasswordAuthenticationToken car c'est ce que le contexte accepte
                SecurityContextHolder.getContext()
                        .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            }
            // Enfin je passe au filtre suivant
            filterChain.doFilter(request, response);
        }
    }
}

