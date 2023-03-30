package com.applicationtrain.applicationtrain.repository;

import com.applicationtrain.applicationtrain.entity.Picture;
import com.applicationtrain.applicationtrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {



}
