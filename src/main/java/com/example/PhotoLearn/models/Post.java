package com.example.PhotoLearn.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column
    private String title;
    @Lob
    @NotBlank
    private String content;
    private Instant createdOn;
    private Instant updatedOn;
    @ManyToOne
    private User user;
    
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public Instant getCreatedOn() {
        return createdOn;
    }
    public Instant getUpdatedOn() {
        return updatedOn;
    }
    public User getUser() {
        return user;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }
    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
