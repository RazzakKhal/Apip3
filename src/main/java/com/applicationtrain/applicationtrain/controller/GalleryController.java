package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/galerie")
public class GalleryController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/femme", method = RequestMethod.GET)
    public List<User> usersByGenderF(){
        List<User> usersFemmes = userRepository.findFemaleByTrainNumber();
        return usersFemmes;
    }

    @RequestMapping(value = "/homme", method = RequestMethod.GET)
    public List<User> usersByGenderM(){
        List<User> usersHommes = userRepository.findMaleByTrainNumber();
        return usersHommes;
    }
}