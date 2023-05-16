package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.Token.JwtUtil;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.AccueilService;
import com.applicationtrain.applicationtrain.service.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/accueil")
public class AccueilController {
    @Autowired
    private  AccueilService accueilService;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public HashMap<String, String> userRegister(@RequestBody User user) {
       return accueilService.userInscription(user);

    }

    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public TokenResponse userConnexion(@RequestBody User user) throws Exception {
        UserDetails userRecup = userRepository.findByMail(user.getMail());
        // ajouter le numero de train récupéré


    if(userRecup != null && bCryptPasswordEncoder.matches(user.getPassword(),userRecup.getPassword())){
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userRecup, null, userRecup.getAuthorities()));
        String token = jwtUtil.generateToken(userRecup);
        User userRecup2 = userRepository.findByMail(user.getMail());
        userRecup2.setTrain_number(user.getTrain_number());
        userRepository.save(userRecup2);
        return new TokenResponse(token);
        } else {
        throw new Exception("Erreur dans l'authentification");
    }
}}
