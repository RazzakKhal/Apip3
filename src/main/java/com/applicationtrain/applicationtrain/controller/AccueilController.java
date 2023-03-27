package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.JwtUtil;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.AccueilService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/accueil")
public class AccueilController {

    private final AccueilService accueilService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public String userRegister(@RequestBody User user) {
        return accueilService.userInscription(user);
    }

    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String userConnexion(@RequestBody User user) {
        UserDetails userRecup = userRepository.findByMail(user.getMail());
        if(userRecup != null){
            String token = jwtUtil.generateToken(userRecup);
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getMail(), user.getPassword()));

                return token;
        }
    else {
        return "mauvaise authentification";
    }
}


}
