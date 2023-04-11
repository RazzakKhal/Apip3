package com.applicationtrain.applicationtrain.repository;

import com.applicationtrain.applicationtrain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


    // SELECT * FROM MESSAGE WHERE IDSENDER=:IDSENDER AND IDRECEIVER=:IDRECEIVER ORDER BY MESSAGE.ID ASC
    @Query("SELECT m FROM Message m WHERE m.messageSender.id = :idSender AND m.messageReceiver.id = :idReceiver OR m.messageSender.id = :idReceiver AND m.messageReceiver.id = :idSender ORDER BY m.id ASC")
    List<Message> findMessages(@Param("idSender") long idSender, @Param("idReceiver") long idReceiver);
}
