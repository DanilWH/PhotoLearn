package com.example.PhotoLearn.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tutorials")
public class Tutorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Это поле должно быть заполнено")
    private String title;

    @Lob
    @Type(type = "text")
    @NotBlank(message = "Это поле должно быть заполнено")
    private String content;

    private Instant createdOn;
    private Instant updatedOn;
    private String imgName;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "tutorial_likes",
            joinColumns = { @JoinColumn(name = "tutorial_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> likes = new HashSet<>();

    public Tutorial() {
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
    public void setUser(User user) {
        this.user = user;
    }
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }
}
