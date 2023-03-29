package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.MyProfilService;
import com.applicationtrain.applicationtrain.service.MyProfilServiceImpl;
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


    //test sandra pour myProfil put
    //je dois recuperer dans la BDD l'utilisateur qui correspond à celui envoyé d'Angular
    //je dois modifier sa proprieté size
    @RequestMapping(value ="/updateSize", method = RequestMethod.PUT)
    public User udapteUserSize(@RequestBody User userAngular){
        User user = userRepository.findByMail(userAngular.getMail());
        user.setSize(userAngular.getSize());
        userRepository.save(user);
        return user;
    }
}
