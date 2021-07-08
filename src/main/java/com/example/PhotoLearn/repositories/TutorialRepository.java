package com.example.PhotoLearn.repositories;

import com.example.PhotoLearn.models.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    Page<Tutorial> findAllByOrderByCreatedOnDesc(Pageable pageable);
    Page<Tutorial> findByTitleContainingIgnoreCase(String filter, Pageable pageable);

}
