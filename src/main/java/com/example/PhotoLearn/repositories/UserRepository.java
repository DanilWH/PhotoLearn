package com.example.PhotoLearn.repositories;

import com.example.PhotoLearn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findByUsername(String username);

    User findByActivationCode(String code);
}
