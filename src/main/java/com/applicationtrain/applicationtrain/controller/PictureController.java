package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.Picture;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.PictureRepository;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;

    @PostMapping(value = "/addpicture")
    public ResponseEntity<List<Picture>> savePicture(@RequestBody Picture imageUrl){ // recupere l'URL de l'img par POST
        Picture picture = new Picture(imageUrl.getLink(), imageUrl.getUser());
        pictureRepository.save(picture);
        User user = userRepository.findByMail(imageUrl.getUser().getMail());

        return ResponseEntity.ok(user.getPictures()); //return une reponse HTTP 200
    }
}
