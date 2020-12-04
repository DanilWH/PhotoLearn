package com.example.PhotoLearn.controllers.teacher;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.repositories.TutorialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.services.TutorialService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@PreAuthorize("hasRole('TEACHER')")
@RequestMapping("/tutorial")
public class TeacherTutorialController {

    @Autowired
    TutorialService tutorialService;
    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/new")
    public String newTutorial(Model model) {
        model.addAttribute("tutorialDto", new TutorialDto());

        return "new_tutorial";
    }

    @PostMapping("/new")
    public String addTutorial(
            @Valid TutorialDto tutorialDto,
            BindingResult bindingResult,
            @RequestParam MultipartFile multipartFile,
            Model model
    ) throws IOException {
        // the fields validation.
        if (bindingResult.hasErrors()) {
            model.addAttribute(tutorialDto);
            return "new_tutorial";
        }

        tutorialService.createNewTutorial(tutorialDto, multipartFile);

        return "redirect:/";
    }

    @GetMapping("/{tutorialId}/edit")
    public String editTutorial(
            @PathVariable Long tutorialId,
            Model model
    ) {
        TutorialDto tutorialDto = this.tutorialService.getDtoById(tutorialId);

        model.addAttribute(tutorialDto);

        return "edit_tutorial";
    }

    @PostMapping("/{tutorialId}/edit")
    public String updateTutorial(
            @PathVariable Long tutorialId,
            @Valid TutorialDto tutorialDto,
            BindingResult bindingResult,
            @RequestParam MultipartFile multipartFile,
            Model model
    ) throws IOException {
        // the fields validation.
        if (bindingResult.hasErrors()) {
            model.addAttribute(tutorialDto);
            return "edit_tutorial";
        }
        Tutorial tutorial = this.tutorialService.getById(tutorialId);

        this.tutorialService.updateExistingTutorial(tutorialDto, tutorial, multipartFile);

        return "redirect:/tutorial/" + tutorialId;
    }

    @GetMapping("/{tutorialId}/delete")
    public String deleteTutorialConfirmation(
            @PathVariable Long tutorialId,
            Model model
    ) {
        TutorialDto tutorialDto = this.tutorialService.getDtoById(tutorialId);
        model.addAttribute(tutorialDto);

        return "delete_tutorial_confirmation";
    }

    @PostMapping("/{tutorialId}/delete")
    public String deleteTutorial(
            @PathVariable Long tutorialId
    ) {
        this.tutorialService.deleteExistingTutorial(tutorialId);

        return "redirect:/";
    }
}