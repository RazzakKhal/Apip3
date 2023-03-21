package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/galerie")
public class GalleryController {
    @Autowired
    GalleryService galleryService;


    @RequestMapping(value = "/femme/{id}", method = RequestMethod.GET)
    public List<User> usersByGenderF(@PathVariable Long id){
      return galleryService.usersByGenderF(id);
    }

    @RequestMapping(value = "/homme", method = RequestMethod.GET)
    public List<User> usersByGenderM(){
       return galleryService.usersByGenderM();
    }
}