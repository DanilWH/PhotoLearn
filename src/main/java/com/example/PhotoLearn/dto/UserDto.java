package com.example.PhotoLearn.dto;

import javax.validation.constraints.NotBlank;

import com.example.PhotoLearn.validation.PasswordMatches;
import com.example.PhotoLearn.validation.ValidPassword;
import com.example.PhotoLearn.validation.ValidUsername;

@PasswordMatches
public class UserDto {
    
    @NotBlank(message = "Имя пользователя не должно быть пустым.")
    @ValidUsername
    private String username;
    @ValidPassword
    private String password;
    private String confirmPassword;
    private boolean active;
    
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getConfirmPassword() {
        return this.confirmPassword;
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
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
