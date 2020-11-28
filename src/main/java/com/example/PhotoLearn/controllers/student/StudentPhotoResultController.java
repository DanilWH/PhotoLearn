package com.example.PhotoLearn.controllers.student;

import com.example.PhotoLearn.dto.PhotoResultDto;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.repositories.TutorialRepository;
import com.example.PhotoLearn.services.PhotoResultService;
import com.example.PhotoLearn.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/tutorial")
public class StudentPhotoResultController {

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private PhotoResultService photoResultService;

    @PostMapping("/{tutorialId}/photo-result/add")
    public String addPhotoResult(
            @PathVariable Long tutorialId,
            @Valid PhotoResultDto photoResultDto,
            @RequestParam MultipartFile multipartFile,
            Model model
    ) throws Exception {
        // find the tutorial that is going to be the parent for the PhotoResult
        // and set it to DTO.
        photoResultDto.setTutorial(this.tutorialService.getById(tutorialId));
        // store the PhotoResult in the database.
        this.photoResultService.savePhotoResult(photoResultDto, multipartFile);

        return "redirect:/tutorial/" + tutorialId;
    }

}
