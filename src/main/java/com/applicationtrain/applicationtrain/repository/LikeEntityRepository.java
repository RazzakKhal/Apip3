package com.applicationtrain.applicationtrain.repository;

import com.applicationtrain.applicationtrain.entity.LikeEntity;
import com.applicationtrain.applicationtrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikeEntityRepository extends JpaRepository<LikeEntity, Long> {

    //Aller dans LikeRepository, créer une méthode avec @Query permettant de récupérer des likes par l'id de l'utilisateur
    @Query("select l, l.likeSender.id, l.likeReceiver.id from LikeEntity l where l.likeSender.id = :idSender")
    List<LikeEntity> findBySenderId(@Param("idSender") Long idSender);


// requete permettant de recuperer chaque like + l'utilisateur ayant envoyé le like
    @Query("SELECT l, l.likeSender FROM LikeEntity l WHERE l.likeReceiver.id = :userId")
    List<Object[]> findLikesByUserId(@Param("userId") Long userId);

    Optional<LikeEntity> findByLikeSenderAndLikeReceiver(User likeSender, User likeReceiver);


}
