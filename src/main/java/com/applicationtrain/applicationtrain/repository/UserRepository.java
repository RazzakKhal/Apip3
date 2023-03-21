package com.applicationtrain.applicationtrain.repository;

import com.applicationtrain.applicationtrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //  SELECT * FROM user WHERE gender = "F" AND train_number = 1890;

    @Query("select u from User u where u.gender = 'F' AND u.train_number = 1850")
    List<User> findFemaleByTrainNumber();
    @Query("select u from User u where u.gender = 'M' AND u.train_number = 1850" )
    List<User> findMaleByTrainNumber();
}
