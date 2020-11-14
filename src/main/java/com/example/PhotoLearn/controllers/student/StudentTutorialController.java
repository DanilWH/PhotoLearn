package com.example.PhotoLearn.controllers.student;

import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.repositories.TutorialRepository;

@Controller
@PreAuthorize("hasRole('STUDENT')")
public class StudentTutorialController {
    
    @Autowired
    private TutorialRepository tutorialRepository;
    
    @GetMapping("/")
    public String index() {
        return "redirect:/tutorials";
    }
    
    @GetMapping("/tutorials")
    public String showTutorials(Model model) {
        List<Tutorial> tutorials = this.tutorialRepository.findAll();
        model.addAttribute("tutorials", tutorials);
        
        return "tutorials";
    }
    
    @GetMapping("/tutorial/{tutorialId}")
    public String showSingleTutorial(
            @PathVariable Long tutorialId,
            Model model
    ) {
        // create the ModelMapper object.
        ModelMapper modelMapper = new ModelMapper();
        
        // find the tutorial by its ID.
        Tutorial tutorial = this.tutorialRepository.findById(tutorialId).orElseThrow(() -> new NoResultException());
        // map the found tutorial into the DTO.
        TutorialDto tutorialDto = modelMapper.map(tutorial, TutorialDto.class);
        
        // put the tutorial DTO into the model.
        model.addAttribute(tutorialDto);
        
        return "tutorial";
    }
}
