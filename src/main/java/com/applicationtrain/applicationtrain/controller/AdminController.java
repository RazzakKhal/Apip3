package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
