package com.example.PhotoLearn.controllers.student;

import com.example.PhotoLearn.controllers.ControllerUtils;
import com.example.PhotoLearn.dto.PhotoResultDto;
import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.models.PhotoResult;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.repositories.PhotoResultRepository;
import com.example.PhotoLearn.repositories.TutorialRepository;
import com.example.PhotoLearn.services.PhotoResultService;
import com.example.PhotoLearn.services.TutorialService;
import com.example.PhotoLearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Controller
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
            @AuthenticationPrincipal User currentUser,
            @RequestParam(name = "filter", defaultValue = "", required = false) String filter,
            @PageableDefault(sort = { "id" }, size = 6, direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        // get all the tutorials that are in the database.
        Page<TutorialDto> page;

        // TODO move the piece code below to the tutorial service.
        // check if the filter isn't empty
        if (filter != null && !filter.trim().isEmpty()) {
            // make the filter to the lower case.
            page = this.tutorialService.getDtoByTitleContainingIgnoreCase(filter, pageable, currentUser);
        } else {
            // get all the tutorials if the filter is empty.
            page = this.tutorialService.getAllDto(pageable, currentUser);
        }

        model.addAttribute("url", "/tutorials");
        // add all the tutorials that have been found so far to the view.
        model.addAttribute("page", page);
        // we also add the search bar value in order to display it
        // in the case if the user used the search bar.
        model.addAttribute("filter", filter);
        model.addAttribute("pageSequence", ControllerUtils.getPagerSequence(page));

        return "tutorials";
    }
    
    @GetMapping("/tutorial/{tutorialId}")
    public String showSingleTutorial(
            @PathVariable Long tutorialId,
            @AuthenticationPrincipal User currentUser,
            Model model
    ) {
        TutorialDto tutorialDto = this.tutorialService.getDtoById(tutorialId, currentUser);

        // put the tutorial DTO into the model.
        model.addAttribute(tutorialDto);

        if (currentUser != null && currentUser.getUserRoles().contains(UserRoles.ROLE_STUDENT)) {
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

    @GetMapping("/tutorial/{tutorialId}/like")
    public String tutorialLike(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Tutorial tutorialId,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer
    ) {
        Set<User> likes = tutorialId.getLikes();

        if (likes.contains(currentUser)) {
            likes.remove(currentUser);
        } else {
            likes.add(currentUser);
        }

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        return "redirect:" + components.getPath();
    }
}
