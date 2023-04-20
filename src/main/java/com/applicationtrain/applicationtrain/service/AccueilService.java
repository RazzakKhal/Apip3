package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import java.util.HashMap;

public interface AccueilService {

    HashMap<String, String> userInscription(User user);

}
