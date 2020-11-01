package com.example.PhotoLearn.services;

import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.web.dto.UserDto;
import com.example.PhotoLearn.web.error.UserAlreadyExistsException;

public interface IUserService {
    
    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistsException;

}
