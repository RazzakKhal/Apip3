package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AccueilService {
    User userInscription(User user);



}
