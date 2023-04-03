package com.applicationtrain.applicationtrain.service;

import com.applicationtrain.applicationtrain.repository.PictureRepository;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PictureServiceImpl implements PictureService{

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public void deleteById(long id) {
        pictureRepository.deleteById(id);

    }


}
