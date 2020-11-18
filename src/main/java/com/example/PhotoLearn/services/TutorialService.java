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

        // set the time that the tutorial was created on.
        Instant createdOn = Instant.now();
        tutorial.setCreatedOn(createdOn);
        tutorial.setUpdatedOn(createdOn);

        tutorial.setImgEditorIsInjected(tutorialDto.isImgEditorIsInjected());

        // set the user that created the tutorial.
        User user = this.userService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException("No user logged in"));
        tutorial.setUser(user);
        
        this.tutorialRepository.save(tutorial);
    }

    public void updateExistingTutorial(TutorialDto tutorialDto, Tutorial tutorial) {
        // set new values to the fields that might be changed.
        tutorial.setTitle(tutorialDto.getTitle());
        tutorial.setContent(tutorialDto.getContent());
        tutorial.setImgEditorIsInjected(tutorialDto.isImgEditorIsInjected());
        tutorial.setUpdatedOn(Instant.now());

        // update the tutorial in the database.
        this.tutorialRepository.save(tutorial);

    }

}
