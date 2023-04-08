package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.Message;

import com.applicationtrain.applicationtrain.entity.User;
import com.applicationtrain.applicationtrain.repository.MessageRepository;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@CrossOrigin
public class TchatController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    // j'envoi le message à une adresse qui contient l'id de l'envoyeur et du receveur
    @MessageMapping("/chat/send/{idSender}/{idReceiver}")
    public void handleMessage(Message message) {

           // j'enregistre le message en base de données en y ajoutant l'envoyeur et le receveur
           messageRepository.save(message);

           // je le renvoi au receveur et à l'envoyeur

        messagingTemplate.convertAndSend("/topic/messages/" + message.getMessageReceiver().getId() + "/" + message.getMessageSender().getId(), message);
        messagingTemplate.convertAndSend("/topic/messages/" + message.getMessageSender().getId() + "/" + message.getMessageReceiver().getId(), message);


    }

}
