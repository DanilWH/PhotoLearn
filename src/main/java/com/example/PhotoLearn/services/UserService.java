package com.example.PhotoLearn.services;

import com.example.PhotoLearn.dto.UserDto;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Value("${mail.activation.address}")
    private String activationAddress;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderService emailSender;

    // TODO refactoring.

    /*** Persistence section ***/

    public User registerNewUserAccount(UserDto accountDto) {
        User user = new User();

        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setActive(true);
        user.setUserRoles(Collections.singleton(UserRoles.ROLE_STUDENT));
        user.setEmail(accountDto.getEmail());
        user.setActivationCode(UUID.randomUUID().toString());

        // store the saved user in a variable for its further returning.
        user = this.userRepository.save(user);

        this.sendMessage(user);
        
        return user;
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
        User user = this.userRepository.findById(userId).orElseThrow(NoResultException::new);

        return new ModelMapper().map(user, UserDto.class);
    }

    public User getById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(NoResultException::new);
    }

    /*** Current user section ***/
    
    public Optional<User> getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.of(principal);
    }

    public User getCurrentUserOrElseThrow() {
        return getCurrentUser().orElseThrow(NoResultException::new);
    }

    /*** Email section ***/

    public void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                    "Добро пожаловать на PhotoLearn! Чтобы подтвердить ваш email, " +
                    "пожалуйста, перейдите по ссылке: %s%s",

                    user.getUsername(),
                    this.activationAddress,
                    user.getActivationCode()
            );

            this.emailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = this.userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        this.userRepository.save(user);

        return true;
    }

}
