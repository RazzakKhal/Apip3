package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.JwtUtil;
import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import com.applicationtrain.applicationtrain.service.AccueilService;
import com.applicationtrain.applicationtrain.service.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> userRegister(@RequestBody User user){
       return ResponseEntity.ok(accueilService.userInscription(user));
    }

    @RequestMapping(value = "/connexion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponse> userConnexion(@RequestBody User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getMail(), user.getPassword()));
      UserDetails userRecup = userRepository.findByMail(user.getMail());
      String token = jwtUtil.generateToken(userRecup);
      return ResponseEntity.ok(new TokenResponse(token));
    }


}
