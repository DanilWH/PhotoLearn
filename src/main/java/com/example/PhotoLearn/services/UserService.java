package com.example.PhotoLearn.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.PhotoLearn.models.CustomUserDetails;
import com.example.PhotoLearn.models.UserDto;
import com.example.PhotoLearn.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDto> userDto = this.userRepository.findByUsername(username);
        
        userDto.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));
        
        return userDto.map(CustomUserDetails::new).get();
    }
    
    @Override
    public UserDto registerNewUserAccount(UserDto accountDto) {
        return new UserDto();
    }

    
}
