package com.example.PhotoLearn.services;

import java.util.Collections;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PhotoLearn.dto.UserDto;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.repositories.UserRepository;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User registerNewUserAccount(UserDto accountDto) {
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setActive(true);
        user.setUserRoles(Collections.singleton(UserRoles.ROLE_STUDENT));
        
        return this.userRepository.save(user);
    }
    
    public Optional<User> getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

    public User getCurrentUserOrElseThrow() {
        return getCurrentUser().orElseThrow(() -> new NoResultException());
    }
    
}
