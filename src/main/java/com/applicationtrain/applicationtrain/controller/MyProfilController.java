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
    MyProfilService myProfilService;

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public User getTokenUser(@RequestBody User user){
       return myProfilService.getUserTokenByMail(user.getMail());
    }
}
