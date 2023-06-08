package com.applicationtrain.applicationtrain.controller;

import com.applicationtrain.applicationtrain.entity.Message;

import com.applicationtrain.applicationtrain.repository.MessageRepository;
import com.applicationtrain.applicationtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@CrossOrigin
@Controller
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

           // je le renvoi au receveur

     //   messagingTemplate.convertAndSend("/topic/messages/" + message.getMessageReceiver().getId() + "/" + message.getMessageSender().getId(), message);
        messagingTemplate.convertAndSend("/topic/messages/" + message.getMessageSender().getId() + "/" + message.getMessageReceiver().getId(), message);

    }

    // faire une requete pour récupérer messages envoyés
    @RequestMapping(value = "messageriesend/{idSender}/{idReceiver}", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> findMyMessagesSend(@PathVariable long idSender, @PathVariable long idReceiver) throws Exception {
        if(messageRepository.findMessagesSend(idSender, idReceiver) != null){
            return messageRepository.findMessagesSend(idSender, idReceiver);
        }else{
            throw new Exception("Messages non trouvés");
        }
    }

    // faire une requete pour récupérer messages recus

    @RequestMapping(value = "messageriereceive/{idSender}/{idReceiver}", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> findMyMessagesReceive(@PathVariable long idSender, @PathVariable long idReceiver) throws Exception {
        if(messageRepository.findMessagesReceived(idSender, idReceiver) != null){
            return messageRepository.findMessagesReceived(idSender, idReceiver);
        }else{
            throw new Exception("Messages non trouvés");
        }
    }

}
