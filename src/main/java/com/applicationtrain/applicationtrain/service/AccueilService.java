package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface AccueilService {



    User userInscription(User user, BCryptPasswordEncoder bCryptPasswordEncoder);
}
