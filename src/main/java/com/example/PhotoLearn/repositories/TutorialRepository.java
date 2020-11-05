package com.example.PhotoLearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PhotoLearn.models.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
