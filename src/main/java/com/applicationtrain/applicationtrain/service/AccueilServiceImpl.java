package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.Token.JwtUtil;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class AccueilServiceImpl implements AccueilService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public HashMap<String, String> userInscription(User user) {
        // si l'utilisateur n'est pas inscrit on l'enregistre en base de données
        if(userRepository.findByMail(user.getMail()) == null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Succes", "Utilisateur Inscris");
            return map;
        }else{
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Succes", "Utilisateur déjà Inscris");
            return map;
        }


    }



}
