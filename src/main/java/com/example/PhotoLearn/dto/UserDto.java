package com.example.PhotoLearn.dto;

import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.validation.PasswordMatches;
import com.example.PhotoLearn.validation.ValidPassword;
import com.example.PhotoLearn.validation.ValidUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@PasswordMatches
public class UserDto {

    private Long id;
    @NotBlank(message = "Имя пользователя не должно быть пустым.")
    @ValidUsername
    private String username;
    @ValidPassword
    private String password;
    private String confirmPassword;
    private Set<UserRoles> userRoles;
    @Email(message = "Invalid email.")
    @NotBlank(message = "This field must not be empty.")
    private String email;
    private String activationCode;

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getConfirmPassword() {
        return this.confirmPassword;
    }
    
    public Set<UserRoles> getUserRoles() {
        return this.userRoles;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public void setUserRoles(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
