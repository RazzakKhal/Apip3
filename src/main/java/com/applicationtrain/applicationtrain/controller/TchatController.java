package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.Message;
import com.applicationtrain.applicationtrain.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class TchatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/chat/send")
    public void handleMessage(Message message) {
        // j'enregistre le message en base de données
        messageRepository.save(message);
        // Je renvoi le message afin que les personnes abonnées à l'url suivante voient le message
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

}
