package com.example.PhotoLearn.services;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.repositories.TutorialRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;

@Service
public class TutorialService {

    @Value("${upload.path}")
    private String uploadPath;
    
    @Autowired
    private UserService userService;
    @Autowired
    private TutorialRepository tutorialRepository;
    
    public void createNewTutorial(TutorialDto tutorialDto, MultipartFile file) throws IOException {
        Tutorial tutorial = new Tutorial();
        
        tutorial.setTitle(tutorialDto.getTitle());
        tutorial.setContent(tutorialDto.getContent().replace("\r\n", ""));

        // set the time that the tutorial was created on.
        Instant createdOn = Instant.now();
        tutorial.setCreatedOn(createdOn);
        tutorial.setUpdatedOn(createdOn);

        tutorial.setImgName(this.uploadImage(file));

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
        // tutorial.setImgEditorIsInjected(tutorialDto.isImgEditorIsInjected()); TODO
        tutorial.setUpdatedOn(Instant.now());

        // update the tutorial in the database.
        this.tutorialRepository.save(tutorial);

    }

    public TutorialDto getDtoById(Long tutorialId) {
        // extract the necessary tutorial from the database.
        Tutorial tutorial = this.getById(tutorialId);

        // map the extracted tutorial into DTO.
        ModelMapper modelMapper = new ModelMapper();
        TutorialDto tutorialDto = modelMapper.map(tutorial, TutorialDto.class);

        return tutorialDto;
    }

    public Tutorial getById(Long tutorialId) {
        return this.tutorialRepository.findById(tutorialId).orElseThrow(() -> new NoResultException());
    }

    private String uploadImage(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            // create a new UUID for the file.
            String fileUUID = UUID.randomUUID().toString();
            // assign the UUID to the filename.
            String imageFilename = fileUUID + "." + file.getOriginalFilename();

            // check if the directory exists on the server.
            File directory = new File(this.uploadPath);
            if (!directory.exists())
                directory.mkdirs();

            // transfer the multipart file to the directory on the server.
            file.transferTo(new File(this.uploadPath + imageFilename));

            // return the image filename as a result.
            return imageFilename;
        }

        // return null if there were problems with getting the multipart file.
        return null;
    }

}
