package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;
//Méthode pour récupérer tous les utilisateurs
    @RequestMapping (value = "/alluser", method = RequestMethod.GET)
   public List<User> findAllUser(){
       return adminService.findAllUser();

    }
//Méthode pour supprimer un utilisateur par son ID
    @RequestMapping (value = "/deleteuser/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable long id, @RequestBody User user){
        // verifier que l'utilisateur qui fait la requete est un ADMIN
        // Recherche de l'utilisateur connecté par son e-mail
        if(userRepository.findByMail(user.getMail()) != null){
            User userConnected= userRepository.findByMail(user.getMail());
            // Verifier si le rôle de l'utilisateur connecté est bien ADMIN
                if(userConnected.getRole() == "ADMIN"){
                    //Supprime l'utilisateur par son ID
                    adminService.deleteUserById(id);
                }
            }

    }

}
