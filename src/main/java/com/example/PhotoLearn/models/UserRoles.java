package com.example.PhotoLearn.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_TEACHER,
    ROLE_STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }

}
