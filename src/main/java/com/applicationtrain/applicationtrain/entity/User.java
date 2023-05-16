package com.applicationtrain.applicationtrain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String role;


    private Date date_of_birth;

    @NotBlank
    private String pseudo;

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String gender;


    private int train_number;

    private int car_number;

    private String description;

    private int size;

    @JsonManagedReference(value ="user_picture")
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Picture> pictures;


    @JsonManagedReference(value ="user_messageSender")
    @OneToMany(mappedBy = "messageSender", cascade = CascadeType.REMOVE)
    private List<Message> messagesSended;


    @JsonManagedReference(value ="user_messageReceiver")
    @OneToMany(mappedBy = "messageReceiver", cascade = CascadeType.REMOVE)
    private List<Message> messagesReceived;

@JsonManagedReference(value ="user_likeSender")
    @OneToMany(mappedBy = "likeSender", cascade = CascadeType.REMOVE)
    private List<LikeEntity> likesSended;

    @JsonManagedReference(value ="user_userReceiver")
    @OneToMany(mappedBy = "likeReceiver", cascade = CascadeType.REMOVE)
    private List<LikeEntity> likesReceived;



    public User() {
    }


    public User(String firstname, String lastname, Date date_of_birth, String pseudo, String mail, String password, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.date_of_birth = date_of_birth;
        this.pseudo = pseudo;
        this.mail = mail;
        this.password = password;
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<LikeEntity> getLikesSended() {
        return likesSended;
    }

    public void setLikesSended(List<LikeEntity> likesSended) {
        this.likesSended = likesSended;
    }

    public List<LikeEntity> getLikesReceived() {
        return likesReceived;
    }

    public void setLikesReceived(List<LikeEntity> likesReceived) {
        this.likesReceived = likesReceived;
    }

    public List<Message> getMessagesSended() {
        return messagesSended;
    }

    public void setMessagesSended(List<Message> messagesSended) {
        this.messagesSended = messagesSended;
    }

    public List<Message> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<Message> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }



    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.getRole()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTrain_number() {
        return train_number;
    }

    public void setTrain_number(int train_number) {
        this.train_number = train_number;
    }

    public int getCar_number() {
        return car_number;
    }

    public void setCar_number(int car_number) {
        this.car_number = car_number;
    }
}
