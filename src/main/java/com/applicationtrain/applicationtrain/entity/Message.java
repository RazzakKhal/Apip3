package com.applicationtrain.applicationtrain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @JsonBackReference(value ="user_messageSender")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "message_sender")
    private User messageSender;

@JsonBackReference(value ="user_messageReceiver")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "message_receiver")
    private User messageReceiver;

    public Message() {
    }

    public Message(String content, User messageSender, User messageReceiver) {
        this.content = content;
        this.messageSender = messageSender;
        this.messageReceiver = messageReceiver;
    }

    public User getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(User messageSender) {
        this.messageSender = messageSender;
    }

    public User getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(User messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
