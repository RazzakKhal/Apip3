package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.LikeEntity;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.LikeEntityRepository;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class GalleryServiceImpl implements GalleryService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeEntityRepository likeEntityRepository;
    @Override
    public List<User> usersByGenderM(Long id){
        Optional<User> male = userRepository.findById(id);
        // récupérer son numero de train
        List<User> usersHommes = userRepository.findMaleByTrainNumber(male.get().getTrain_number());
        return usersHommes;
    }
    @Override
    public List<User> usersByGenderF(Long id) {
        // recupérer l'utilisateur connecté
        Optional<User> female = userRepository.findById(id);
        // récupérer son numero de train
        List<User> usersFemmes = userRepository.findFemaleByTrainNumber(female.get().getTrain_number());
        return usersFemmes;
    }


    /*@Override
    public HashMap<String, String> sendLike(long id, User user){
    Optional<User> userOptional = userRepository.findById(id);
    if(userOptional != null){
        User userReceived = userOptional.get();
        LikeEntity like = new LikeEntity(user,userReceived);
        likeEntityRepository.save(like);
       HashMap<String,String> reponse = new HashMap<String,String>();
        reponse.put("succes", "like enregistré");
        return reponse;

    }else{
        HashMap<String,String> reponse = new HashMap<String,String>();
        reponse.put("erreur", "like non enregistré");
        return reponse;
    }*/

    @Override
    public HashMap<String, String> sendLike(long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        HashMap<String, String> reponse = new HashMap<>();

        if (userOptional.isPresent()) {
            User userReceived = userOptional.get();
            Optional<LikeEntity> existingLike = likeEntityRepository.findByLikeSenderAndLikeReceiver(user, userReceived);

            if (existingLike.isPresent()) {
                reponse.put("erreur", "like déjà enregistré");
            } else {
                LikeEntity like = new LikeEntity(user, userReceived);
                likeEntityRepository.save(like);
                reponse.put("succes", "like enregistré");
            }
        } else {
            reponse.put("erreur", "like non enregistré");
        }

        return reponse;
    }



    }
