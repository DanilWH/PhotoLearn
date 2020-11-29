package com.example.PhotoLearn.repositories;

import com.example.PhotoLearn.models.PhotoResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoResultRepository extends JpaRepository<PhotoResult, Long> {

    List<PhotoResult> findByTutorialId(Long tutorialId);
    PhotoResult findByTutorialIdAndUserId(Long tutorialId, Long userId);

}
