package com.example.PhotoLearn.services;

import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.web.dto.UserDto;

public interface IUserService {
    
    User registerNewUserAccount(UserDto accountDto);

}
