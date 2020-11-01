package com.example.PhotoLearn.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PhotoLearn.models.UserDto;

public interface UserRepository extends JpaRepository<UserDto, Long> {
    
    Optional<UserDto> findByUsername(String username);

}
