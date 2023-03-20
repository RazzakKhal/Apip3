package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/accueil")
public class AccueilController {

    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public User userInscription(@RequestBody User user){
        userRepository.save(user);
        return user;
    }
}
