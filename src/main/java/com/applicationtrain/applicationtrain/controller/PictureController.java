package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.Picture;
import com.applicationtrain.applicationtrain.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @PostMapping(value = "/addpicture")
    public ResponseEntity<Picture> savePicture(@RequestBody Picture imageUrl){ // recupere l'URL de l'img par POST
        Picture picture = new Picture(imageUrl.getLink(), imageUrl.getUser());
        pictureRepository.save(picture);
        return ResponseEntity.ok(picture); //return une reponse HTTP 200
    }
}
