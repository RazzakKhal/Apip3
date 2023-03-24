package com.applicationtrain.applicationtrain.repository;

import com.applicationtrain.applicationtrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u where u.firstname = :prenom")
    User findByfifi(@Param("prenom") String first);

    @Query("select u from User u where u.gender = 'F' AND u.train_number = :nombre")
    List<User> findFemaleByTrainNumber(@Param("nombre") int trainNumber);
    @Query("select u from User u where u.gender = 'M' AND u.train_number = 1850" )
    List<User> findMaleByTrainNumber();

    User findByMail(String mail);

}
