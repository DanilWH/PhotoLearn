package com.example.PhotoLearn.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import com.example.PhotoLearn.models.User;

public class TutorialDto {
    
    @NotBlank(message = "Это поле должно быть заполнено")
    private String title;
    @NotBlank(message = "Это поле должно быть заполнено")
    private String content;
    private Instant createdOn;
    private Instant updatedOn;
    private User user;
    
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
