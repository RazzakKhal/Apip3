package com.applicationtrain.applicationtrain.repository;

import com.applicationtrain.applicationtrain.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeEntityRepository extends JpaRepository<LikeEntity, Long> {

    //Aller dans LikeRepository, créer une méthode avec @Query permettant de récupérer des likes par l'id de l'utilisateur
    @Query("select l from LikeEntity l where l.likeSender = :idSender")
    List<LikeEntity> findBySenderId(@Param("idSender") long idSender);



}
