package com.example.PhotoLearn.controllers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.repositories.TutorialRepository;
import com.example.PhotoLearn.services.TutorialService;

@Controller
public class TutorialController {
    
    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private TutorialService tutorialService;
    
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
    
    @GetMapping("/tutorial/new")
    public String newTutorial(Model model) {
        model.addAttribute("tutorialDto", new TutorialDto());
        
        return "new_tutorial";
    }
    
    @PostMapping("/tutorial/new")
    public String addTutorial(
            @Valid TutorialDto tutorialDto,
            BindingResult bindingResult,
            Model model
    ) {
        // check if the fields have errors.
        if (bindingResult.hasErrors()) {
            model.addAttribute(tutorialDto);
            return "new_tutorial";
        }
        
        tutorialService.createNewTutorial(tutorialDto);
        
        return "redirect:/";
    }

}
