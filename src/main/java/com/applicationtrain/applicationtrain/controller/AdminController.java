package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
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

    @RequestMapping (value = "/alluser", method = RequestMethod.GET)
   public List<User> findAllUser(){
       return adminService.findAllUser();

    }

    @RequestMapping (value = "/deleteuser/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable long id){
        // verifier que l'utilisateur qui fait la requete est un ADMIN
        adminService.deleteUserById(id);
    }
    /*verifier que l'utilisateur qui fait la requete est un ADMIN*/
}
