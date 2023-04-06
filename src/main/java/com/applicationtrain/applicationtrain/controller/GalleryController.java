package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.LikeEntity;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/galerie")
public class GalleryController {
    @Autowired
    GalleryService galleryService;

    @Autowired
    UserRepository userRepository;

//récupérer les femmes en fonction du numéro de train de la femme qui envoi la requete
    @RequestMapping(value = "/femme/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> usersByGenderF(@PathVariable Long id){

        return galleryService.usersByGenderF(id);
    }

    @RequestMapping(value = "/homme/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> usersByGenderM(@PathVariable Long id){
       return galleryService.usersByGenderM(id);
    }

    @RequestMapping(value = "/testtoken", method = RequestMethod.GET)
    public String testToken(){
        return "vous etes correctement authentifié";
    }

    @RequestMapping(value = "/like/{id}", method = RequestMethod.POST)
    public HashMap<String, String> sendLike(@PathVariable long id, @RequestBody User connectedUser){
        return galleryService.sendLike(id, connectedUser);

    }



    // methode qui permet de récuperer les likes de la personne connecté
    // on va savoir qui est la personne connecté par son id
}