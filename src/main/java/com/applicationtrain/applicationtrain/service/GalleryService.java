package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.entity.User;

import java.util.List;

public interface GalleryService {

    List<User> usersByGenderM(Long id);

    List<User> usersByGenderF(Long id);
}
