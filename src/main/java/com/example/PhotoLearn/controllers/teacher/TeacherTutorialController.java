package com.example.PhotoLearn.controllers.teacher;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.services.TutorialService;

@Controller
@PreAuthorize("hasRole('TEACHER')")
public class TeacherTutorialController {
    
    @Autowired
    TutorialService tutorialService;
    
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
