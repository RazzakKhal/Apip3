package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccueilServiceImpl implements AccueilService, UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public User userInscription(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    // methode qui récupere un utilisateur par son mail et renvoi un User de type UserDetail avec les informations d'authentification
    @Override
    public UserDetails loadUserByUsername(String mail){
      User userFind = userRepository.findByMail(mail);
        return new org.springframework.security.core.userdetails.User(userFind.getMail(), userFind.getPassword(), new ArrayList<>());
    }
}
