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
    
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getConfirmPassword() {
        return this.confirmPassword;
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
}
