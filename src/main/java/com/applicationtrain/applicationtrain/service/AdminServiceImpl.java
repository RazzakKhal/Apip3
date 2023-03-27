package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    @Override
    public List<User> findAllUser() {
      return userRepository.findAll();

    }
}
