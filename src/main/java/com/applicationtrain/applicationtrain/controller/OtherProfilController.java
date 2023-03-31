package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("otherProfil")
@CrossOrigin
@RestController
public class OtherProfilController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable long id) throws Exception {
      Optional<User> user = userRepository.findById(id);
      if(user != null){
         return user.get();
      }else{
         throw new Exception("Utilisateur non trouvé");
      }
    }

}
