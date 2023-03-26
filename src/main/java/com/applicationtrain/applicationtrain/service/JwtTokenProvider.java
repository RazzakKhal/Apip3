package com.applicationtrain.applicationtrain.service;

import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    //Les annotations @Value permettent d'injecter des valeurs de propriétés externes
    // (généralement définies dans un fichier application.properties ou application.yml)
    @Value("${app.jwt.expiration}")
    private long jwtExpirationInMillis;

    public String generateToken(Authentication authentication) {
        //La méthode generateToken prend en paramètre un objet Authentication et extrait le UserDetails principal
        // à partir de celui-ci. L'objet UserDetails
        // contient des informations sur l'utilisateur authentifié, telles que le nom d'utilisateur et les autorités.

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMillis);

      //  Ici, nous utilisons la bibliothèque Jwts pour créer le token JWT. Nous définissons le nom d'utilisateur
        //  comme sujet du token, la date d'émission, la date d'expiration et signons le token avec l'algorithme HS512
        //  et la clé secrète. Enfin, nous appelons compact() pour générer le token JWT sous forme de chaîne de caractères.
        return Jwts.builder()
                .setSubject(userPrincipal.getName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    //La méthode getUsernameFromJwt prend en paramètre un token JWT et extrait le nom d'utilisateur
    // (sujet) à partir de celui-ci. Nous utilisons la bibliothèque Jwts pour analyser le token, vérifier
    // la signature avec la clé secrète et récupérer les Claims. Ensuite, nous retournons le sujet (nom d'utilisateur)
    // des claims.
    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    //La méthode validateToken prend en paramètre un token JWT et vérifie sa validité. Elle tente d'analyser le token en
    // utilisant la clé secrète pour vérifier la signature. Si l'analyse réussit sans lancer d'exceptions, cela signifie
    // que le token est valide et la méthode retourne true. Si une exception est levée lors de l'analyse, cela signifie
    // que le token est invalide (par exemple, expiré, mal formé, signature incorrecte, etc.) et la méthode retourne false.
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | ExpiredJwtException ex) {
            // Log exception
        }
        return false;
    }
}