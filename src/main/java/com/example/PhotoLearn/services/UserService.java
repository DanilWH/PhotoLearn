package com.example.PhotoLearn.services;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.repositories.UserRepository;
import com.example.PhotoLearn.web.dto.UserDto;
import com.example.PhotoLearn.web.error.UserAlreadyExistsException;

@Service
@Transactional
public class UserService implements IUserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistsException {
        if (usernameExists(accountDto.getUsername())) {
            throw new UserAlreadyExistsException("There is an account with that email address: "
                                                 + accountDto.getUsername());
        }
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setActive(true);
        user.setUserRoles(Collections.singleton(UserRoles.STUDENT));
        
        return this.userRepository.save(user);
    }
    
    public boolean usernameExists(String username) {
        return this.userRepository.findByUsername(username).orElse(null) != null;
    }

}
