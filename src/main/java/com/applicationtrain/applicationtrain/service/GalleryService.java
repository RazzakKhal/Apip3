package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;
import java.util.HashMap;
import java.util.List;

public interface GalleryService {

    List<User> usersByGenderM(Long id);

    List<User> usersByGenderF(Long id);

    public HashMap<String, String> sendLike(long id, User user);
}
