package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyProfilServiceImpl implements MyProfilService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserTokenByMail(String mail){
      User user=  userRepository.findByMail(mail);
        return user;
    }

        }
