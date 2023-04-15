package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.LikeEntityRepository;
import com.applicationtrain.applicationtrain.repository.UserRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface GalleryService {

    List<User> usersByGenderM(Long id);

    List<User> usersByGenderF(Long id);

    public HashMap<String, String> sendLike(long id, User user);
}
