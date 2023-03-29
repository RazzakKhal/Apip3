package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

public interface AccueilService {



    HashMap<String, String> userInscription(User user);
}
