package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.AccueilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/accueil")
public class AccueilController {

    @Autowired
    private AccueilService accueilService;

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public User userInscription(@RequestBody User user){
        return accueilService.userInscription(user);

    }

    public String userConnexion(@RequestBody Object connexionForm){
        // si les informations sont les memes
        //User user = userRepository.findByMail(connexionForm.mail) recuperation du meme utilisateur
        // connexionForm.password == user.password
        return "LE TOKEN";

        // sinon return "Mauvais identifiant ou mot de passe"
    }
}
