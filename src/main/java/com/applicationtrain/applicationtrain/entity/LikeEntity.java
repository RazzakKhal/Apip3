package com.applicationtrain.applicationtrain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "like_sender")
    private User likeSender;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "like_receiver")
    private User likeReceiver;


    public LikeEntity() {
    }

    public LikeEntity(User likeSender, User likeReceiver) {
        this.likeSender = likeSender;
        this.likeReceiver = likeReceiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
