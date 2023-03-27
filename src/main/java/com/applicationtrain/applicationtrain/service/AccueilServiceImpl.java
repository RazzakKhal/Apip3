package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.JwtUtil;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class AccueilServiceImpl implements AccueilService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public String userInscription(User user) {
        // si l'utilisateur n'est pas inscrit on l'enregistre en base de données
        if(userRepository.findByMail(user.getMail()) == null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            var saveduser = userRepository.save(user);
            return "Utilisateur inscris";
        }else{
            return "Utilisateur déjà inscrit";
        }


    }



}
