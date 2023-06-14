package com.applicationtrain.applicationtrain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

@JsonBackReference(value ="user_likeSender")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "like_sender")
    private User likeSender;

@JsonBackReference(value ="user_userReceiver")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "like_receiver")
    private User likeReceiver;


    public LikeEntity() {
    }

    public LikeEntity(User likeSender, User likeReceiver) {
        this.likeSender = likeSender;
        this.likeReceiver = likeReceiver;
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getLikeSender() {
        return likeSender;
    }

    public void setLikeSender(User likeSender) {
        this.likeSender = likeSender;
    }

    public User getLikeReceiver() {
        return likeReceiver;
    }

    public void setLikeReceiver(User likeReceiver) {
        this.likeReceiver = likeReceiver;
    }
}
