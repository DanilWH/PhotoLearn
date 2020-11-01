package com.example.PhotoLearn.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    
    STUDENT,
    TEACHER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
