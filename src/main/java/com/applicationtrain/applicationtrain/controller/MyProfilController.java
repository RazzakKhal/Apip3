package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.MyProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/myProfil")
public class MyProfilController {
@Autowired
UserRepository userRepository;
    @Autowired
    MyProfilService myProfilService;

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public User getTokenUser(@RequestBody User user){
       return myProfilService.getUserTokenByMail(user.getMail());
    }

    //je dois recuperer dans la BDD l'utilisateur qui correspond à celui envoyé d'Angular
    //je dois modifier sa proprieté size
    @RequestMapping(value ="/updateSize", method = RequestMethod.PUT)
    public User udapteUserSize(@RequestBody User userAngular){
        User user = userRepository.findByMail(userAngular.getMail());
        user.setSize(userAngular.getSize());
        userRepository.save(user);
        return user;
    }

    //nous devons recuperer de la BDD user qui correspond à celui d'angular
    // nous devons modifier sa propriété description
    @RequestMapping(value ="/updateDescription", method = RequestMethod.PUT)
    public User updateUserDescription(@RequestBody User userAngular){
        User user = userRepository.findByMail(userAngular.getMail());
        user.setDescription(userAngular.getDescription());
        userRepository.save(user);
        return user;
    }

    //changement Train number de l'user
    @RequestMapping(value ="/updateNumberTrain", method = RequestMethod.PUT)
    public User updateNumberTrain(@RequestBody User userAngular){
        User user = userRepository.findByMail(userAngular.getMail());
        user.setTrain_number(userAngular.getTrain_number());
        userRepository.save(user);
        return user;
    }


    //changement voiture du train de l'utilisateur
    @RequestMapping(value ="/updateCarTrain", method = RequestMethod.PUT)
    public User updateCarTrain(@RequestBody User userAngular){
        User user = userRepository.findByMail(userAngular.getMail());
        user.setCar_number(userAngular.getCar_number());
        userRepository.save(user);
        return user;
    }


}
