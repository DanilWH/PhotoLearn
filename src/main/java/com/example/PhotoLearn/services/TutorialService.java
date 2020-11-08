package com.example.PhotoLearn.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.repositories.TutorialRepository;

@Service
public class TutorialService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private TutorialRepository tutorialRepository;
    
    public void createNewTutorial(TutorialDto tutorialDto) {
        Tutorial tutorial = new Tutorial();
        
        tutorial.setTitle(tutorialDto.getTitle());
        tutorial.setContent(tutorialDto.getContent().replace("\r\n", ""));
        tutorial.setCreatedOn(Instant.now());
        User user = this.userService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException("No user logged in"));
        tutorial.setUser(user);
        
        this.tutorialRepository.save(tutorial);
    }

}
