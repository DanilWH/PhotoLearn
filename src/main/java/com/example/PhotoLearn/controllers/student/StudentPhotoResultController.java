package com.example.PhotoLearn.controllers.student;

import com.example.PhotoLearn.dto.PhotoResultDto;
import com.example.PhotoLearn.models.PhotoResult;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.repositories.TutorialRepository;
import com.example.PhotoLearn.services.PhotoResultService;
import com.example.PhotoLearn.services.TutorialService;
import com.example.PhotoLearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/tutorial")
public class StudentPhotoResultController {

    @Autowired
    private TutorialService tutorialService;
    @Autowired
    private PhotoResultService photoResultService;
    @Autowired
    private UserService userService;

    @PostMapping("/{tutorialId}/photo-result/add")
    public String addPhotoResult(
            @PathVariable Long tutorialId,
            @Valid PhotoResultDto photoResultDto,
            BindingResult bindingResult,
            @RequestParam MultipartFile multipartFile,
            Model model
    ) throws Exception {
        // forbid adding a PhotoResult if the current user is not a student.
        // (in the case if a teacher or the admin got an accidentional access to the mapping)
        if (!this.userService.getCurrentUserOrElseThrow().isStudent())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        // check if the description length is less than 200 characters.
        if (bindingResult.hasErrors()) {
            model.addAttribute("tutorialDto", this.tutorialService.getDtoById(tutorialId));
            model.addAttribute("photoResultDto", photoResultDto);
            return "tutorial";
        }

        // find the tutorial that is going to be the parent for the PhotoResult
        // and set it to DTO.
        photoResultDto.setTutorial(this.tutorialService.getById(tutorialId));
        // store the PhotoResult in the database.
        this.photoResultService.savePhotoResult(photoResultDto, multipartFile);

        return "redirect:/tutorial/" + tutorialId;
    }

    @GetMapping("/{tutorialId}/photo-result/{photoResultId}/delete")
    public String deletePhotoResult(
            @PathVariable Long tutorialId,
            @PathVariable Long photoResultId,
            Model model
    ) {
        this.photoResultService.deletePhotoResult(photoResultId);

        return "redirect:/tutorial/" + tutorialId;
    }

}
