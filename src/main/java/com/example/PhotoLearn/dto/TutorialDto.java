package com.example.PhotoLearn.dto;

import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.models.User;

import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

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
    private Long likes;
    private Boolean meLiked;

    public TutorialDto() {
    }

    public TutorialDto(Tutorial tutorial, Long likes, Boolean meLiked) {
        this.id = tutorial.getId();
        this.title = tutorial.getTitle();
        this.content = tutorial.getContent();
        this.createdOn = tutorial.getCreatedOn();
        this.updatedOn = tutorial.getUpdatedOn();
        this.imgName = tutorial.getImgName();
        this.user = tutorial.getUser();

        this.likes = likes;
        this.meLiked = meLiked;
    }

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

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    /*** Setters for testing ***/

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

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public void setMeLiked(Boolean meLiked) {
        this.meLiked = meLiked;
    }

    @Override
    public String toString() {
        return "TutorialDto{" +
                "id=" + id +
                ", user=" + user +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}
