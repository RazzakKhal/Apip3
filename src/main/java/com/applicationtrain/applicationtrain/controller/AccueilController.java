package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.AccueilService;
import com.applicationtrain.applicationtrain.service.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/accueil")
public class AccueilController {

    @Autowired
    private AccueilService accueilService;

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;


    AccueilController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider){
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public User userInscription(@RequestBody User user){
        return accueilService.userInscription(user);

    }

    //La méthode authenticateUser est mappée sur la route /signin avec une méthode HTTP POST. Elle prend en paramètre un objet
    // AuthRequest qui est automatiquement construit à partir du corps de la requête JSON grâce à l'annotation @RequestBody.
    // Ensuite, nous utilisons l'AuthenticationManager pour authentifier
    // l'utilisateur en créant un nouvel objet UsernamePasswordAuthenticationToken avec le nom d'utilisateur et le mot de passe fournis.

    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String userConnexion(@RequestBody Object connexionForm){


        return "LE TOKEN";


    }
}
