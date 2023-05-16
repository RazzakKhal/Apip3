package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.Picture;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.PictureRepository;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService{

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteById(long id, User user) {
        // je recupere l'utilisateur en base de donnée

        Optional<User> userrecovered = userRepository.findById(user.getId());
        if(userrecovered.isPresent()){
          User userrecovered2 = userrecovered.get();


            // je vérifie que la photo que l'utilisateur veut supprimer lui appartienne bien
            List<Picture> pictures = userrecovered2.getPictures();
            for(Picture picture : pictures){
                if(picture.getId() == id){
                    pictureRepository.deleteById(id);
                }
            }

        }
    }


}
