package com.example.PhotoLearn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PhotoLearn.models.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    
    List<Tutorial> findAll();
    List<Tutorial> findByTitleContaining(String title);
    
}
