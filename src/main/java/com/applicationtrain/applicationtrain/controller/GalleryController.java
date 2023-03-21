package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/galerie")
public class GalleryController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/femme/{id}", method = RequestMethod.GET)
    public List<User> usersByGenderF(@PathVariable Long id){
        // recupérer l'utilisateur connecté
       Optional<User> female = userRepository.findById(id);
        // récupérer son numero de train
        List<User> usersFemmes = userRepository.findFemaleByTrainNumber(female.get().getTrain_number());
        return usersFemmes;
    }

    @RequestMapping(value = "/homme", method = RequestMethod.GET)
    public List<User> usersByGenderM(){
        List<User> usersHommes = userRepository.findMaleByTrainNumber();
        return usersHommes;
    }
}