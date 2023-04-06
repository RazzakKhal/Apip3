package com.applicationtrain.applicationtrain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "LONGTEXT")
    private String link;

    @JsonBackReference(value ="user_picture")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Picture() {
    }

    public Picture(String link, User user) {
        this.link = link;
        this.user = user;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setImageUrl(String imageUrl) {
    }
}
