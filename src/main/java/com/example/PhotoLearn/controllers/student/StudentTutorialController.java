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
            @RequestParam(name = "tutorial-search",
                    defaultValue = "", required = false) String tutorialSearch,
            Model model
    ) {
        // get all the tutorials that are in the database.
        List<TutorialDto> tutorialsDto = this.tutorialService.getAllDto();

        // check if the filter isn't empty
        if (tutorialSearch != null && !tutorialSearch.trim().isEmpty()) {
            // make the filter to the lower case.
            tutorialSearch = tutorialSearch.toLowerCase();
            // initialize an empty ArrayList for the tutorials that will be found.
            List<TutorialDto> foundTutorials = new ArrayList<>();

            // look for the tutorials whose titles match the filter.
            for (TutorialDto tutorialDto : tutorialsDto)
                if (tutorialDto.getTitle().toLowerCase().contains(tutorialSearch))
                    foundTutorials.add(tutorialDto);

            // add the total number of tutorials that were found
            // and the number of tutorials that were found by the filter.
            model.addAttribute("totalTutorialsNumber", tutorialsDto.size());
            model.addAttribute("foundTutorialsNumber", foundTutorials.size());

            // reuse the old variable for the list of the found tutorial.
            tutorialsDto = foundTutorials;
        }

        // add all the tutorials that have been found so far to the view.
        model.addAttribute("tutorials", tutorialsDto);
        // we also add the search bar value in order to display it
        // in the case if the user used the search bar.
        model.addAttribute("tutorialsSearchValue", tutorialSearch);
        
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
