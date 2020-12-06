package com.example.PhotoLearn.services;

import com.example.PhotoLearn.dto.UserDto;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // TODO refactoring.

    /*** Persistence section ***/

    public User registerNewUserAccount(UserDto accountDto) {
        User user = new User();

        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setActive(true);
        user.setUserRoles(Collections.singleton(UserRoles.ROLE_STUDENT));
        
        return this.userRepository.save(user);
    }

    public User updateUserRoles(Long userId, Set<UserRoles> userRoles) {
        User user = this.getById(userId);

        user.setUserRoles(userRoles);

        return this.userRepository.save(user);
    }

    /*** Getting a user section ***/

    public List<UserDto> getAllAsDto() {
        List<UserDto> usersDto = this.userRepository.findAll().stream().map(user ->
            new ModelMapper().map(user, UserDto.class)
        ).collect(Collectors.toList());

        return usersDto;
    }

    public UserDto getDtoById(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new NoResultException());

        return new ModelMapper().map(user, UserDto.class);
    }

    public User getById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NoResultException());
    }

    /*** Current user section ***/
    
    public Optional<User> getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.of(principal);
    }

    public User getCurrentUserOrElseThrow() {
        return getCurrentUser().orElseThrow(() -> new NoResultException());
    }
    
}
