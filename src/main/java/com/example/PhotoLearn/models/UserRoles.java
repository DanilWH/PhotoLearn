package com.example.PhotoLearn.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    
    ROLE_STUDENT,
    ROLE_TEACHER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
