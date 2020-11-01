package com.example.PhotoLearn.web.dto;

import javax.validation.constraints.NotBlank;

import com.example.PhotoLearn.validation.ValidPassword;

public class UserDto {
    
    @NotBlank(message = "Имя пользователя не должно быть пустым.")
    private String username;
    @ValidPassword
    private String password;
    private boolean active;
    
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
