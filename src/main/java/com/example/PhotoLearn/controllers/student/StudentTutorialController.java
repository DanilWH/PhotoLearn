package com.example.PhotoLearn.controllers.student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import com.example.PhotoLearn.dto.PhotoResultDto;
import com.example.PhotoLearn.models.PhotoResult;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.repositories.PhotoResultRepository;
import com.example.PhotoLearn.services.PhotoResultService;
import com.example.PhotoLearn.services.TutorialService;
import com.example.PhotoLearn.services.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasRole('STUDENT')")
public class StudentTutorialController {

    @Autowired
    private TutorialService tutorialService;
    @Autowired
    private PhotoResultService photoResultService;
    @Autowired
    private UserService userService;

    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private PhotoResultRepository photoResultRepository;
    
    @GetMapping("/")
    public String index() {
        return "redirect:/tutorials";
    }
    
    @GetMapping("/tutorials")
    public String showTutorials(
            @RequestParam(value = "tutorialSearchBar", required = false) String tutorialSearchBar,
            Model model
    ) {
        List<TutorialDto> tutorialsDto = this.tutorialRepository.findAll().stream().map(entity ->
            new ModelMapper().map(entity, TutorialDto.class)
        ).collect(Collectors.toList());

        if (!tutorialSearchBar.trim().isEmpty()) {
            List<TutorialDto> requiredTutorials = new ArrayList<>();

            for (TutorialDto tutorialDto : tutorialsDto)
                if (tutorialDto.getTitle().contains(tutorialSearchBar))
                    requiredTutorials.add(tutorialDto);

            tutorialsDto = requiredTutorials;
        }

        model.addAttribute("tutorials", tutorialsDto);
        
        return "tutorials";
    }
    
    @GetMapping("/tutorial/{tutorialId}")
    public String showSingleTutorial(
            @PathVariable Long tutorialId,
            Model model
    ) {
        TutorialDto tutorialDto = this.tutorialService.getDtoById(tutorialId);

        // put the tutorial DTO into the model.
        model.addAttribute(tutorialDto);

        User currentUser = this.userService.getCurrentUserOrElseThrow();

        if (currentUser.getUserRoles().contains(UserRoles.ROLE_STUDENT)) {
            // find the "photo result" by its tutorial id and user id.
            PhotoResult photoResult = this.photoResultRepository.findByTutorialIdAndUserId(tutorialId, currentUser.getId());
            // find out whether we need to put the DTO of the photo result
            // that is already in the database or we need to create a new DTO.
            PhotoResultDto photoResultDto = (photoResult != null)? this.photoResultService.mapFromEntityToDto(photoResult) : new PhotoResultDto();

            // put a new DTO instance of the user photo result.
            model.addAttribute("photoResultDto", photoResultDto);
        }

        return "tutorial";
    }
}
