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


    /* Interroge la BDD et Recupere les femmes du meme train */
    @Query("select u from User u where u.gender = 'F' AND u.train_number = :nombre")
    List<User> findFemaleByTrainNumber(@Param("nombre") int trainNumber);

    /* Interroge la BDD et Recupere les hommes du meme train */
    @Query("select u from User u where u.gender = 'M' AND u.train_number = :nombre")
    List<User> findMaleByTrainNumber(@Param("nombre") int trainNumber);

    <Optional>User findByMail(String mail);



}
