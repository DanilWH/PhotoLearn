package com.example.PhotoLearn.controllers.teacher;

import com.example.PhotoLearn.dto.PhotoResultDto;
import com.example.PhotoLearn.models.PhotoResult;
import com.example.PhotoLearn.repositories.PhotoResultRepository;
import com.example.PhotoLearn.services.TutorialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasRole('TEACHER')")
@RequestMapping("/tutorial")
public class TeacherPhotoResultController {

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private PhotoResultRepository photoResultRepository;

    @GetMapping("/{tutorialId}/photo-results")
    public String showPhotoResults(
            @PathVariable Long tutorialId,
            Model model
    ) {
        List<PhotoResultDto> photoResultsDto = this.photoResultRepository
                .findByTutorialId(tutorialId)
                .stream().map(photoResult -> {
                    return new ModelMapper().map(photoResult, PhotoResultDto.class);
                })
                .collect(Collectors.toList());

        model.addAttribute("photoResultsDto", photoResultsDto);
        model.addAttribute("tutorialDto", this.tutorialService.getDtoById(tutorialId));

        return "photo_results";
    }
}
