package com.example.PhotoLearn.dto;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.example.PhotoLearn.models.User;

public class TutorialDto {

    private Long id;
    @NotBlank(message = "Это поле должно быть заполнено")
    private String title;
    @NotBlank(message = "Это поле должно быть заполнено")
    private String content;
    private Instant createdOn;
    private Instant updatedOn;
    private String imgName;
    private User user;

    public String getFormattedCreatedOn(Instant instantDate) {
        Date date = Date.from(instantDate);

        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, Y HH:mm");
        return formatter.format(date);
    }

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
    public String getImgName() {
        return imgName;
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
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
