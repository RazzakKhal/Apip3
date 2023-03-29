package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.JwtUtil;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.AccueilService;
import com.applicationtrain.applicationtrain.service.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    public HashMap<String, String> userRegister(@RequestBody User user) {
         accueilService.userInscription(user);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Succes", "Utilisateur Inscris");
         return map;
    }

    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public TokenResponse userConnexion(@RequestBody User user) throws Exception {
        UserDetails userRecup = userRepository.findByMail(user.getMail());
        if(userRecup != null){
            String token = jwtUtil.generateToken(userRecup);
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getMail(), user.getPassword()));

                return new TokenResponse(token);
        }
    else {
        throw new Exception("Erreur dans l'authentification");
    }
}


}
