package com.example.PhotoLearn.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tutorials")
public class Tutorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    private String content;
    private Instant createdOn;
    private Instant updatedOn;
    private boolean imgEditorIsInjected;
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
    public boolean isImgEditorIsInjected() {
        return imgEditorIsInjected;
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
    public void setImgEditorIsInjected(boolean imgEditorIsInjected) {
        this.imgEditorIsInjected = imgEditorIsInjected;
    }
}
