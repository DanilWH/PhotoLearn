package com.example.PhotoLearn.dto;

import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.models.User;
import javax.validation.constraints.Size;

public class PhotoResultDto {

    private Long id;
    @Size(max = 200, message = "Описание не должно быть длиной больше чем 200 символов.")
    private String description;
    private String filename;
    private Tutorial tutorial;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
