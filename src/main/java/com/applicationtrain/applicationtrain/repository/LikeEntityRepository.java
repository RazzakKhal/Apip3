package com.applicationtrain.applicationtrain.repository;

import com.applicationtrain.applicationtrain.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeEntityRepository extends JpaRepository<LikeEntity, Long> {
}
