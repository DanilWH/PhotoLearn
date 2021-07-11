package com.example.PhotoLearn.services;

import com.example.PhotoLearn.dto.TutorialDto;
import com.example.PhotoLearn.models.Tutorial;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.repositories.TutorialRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.time.Instant;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class TutorialServiceTest {
    @Autowired
    private TutorialService tutorialService;

    @MockBean
    private TutorialRepository tutorialRepository;

    @MockBean
    private UserService userService;

    @Test
    public void createNewTutorial() throws Exception {
        /***
         * Mock preparation.
         ***/

        // mock the logged in user.
        Mockito.when(this.userService.getCurrentUserOrElseThrow()).thenReturn(new User());

        // mock the save() method and answer the final tutorial instance.
        Mockito.when(this.tutorialRepository.save(any(Tutorial.class))).then(returnsFirstArg());

        // create a new tutorial that is going to be used for testing.
        TutorialDto tutorialDto = new TutorialDto();
        tutorialDto.setContent("Content of the tutorial.");

        // create a counterfeit multipart file for testing.
        MockMultipartFile multipartFile = new MockMultipartFile("file", "file.txt",
                MediaType.TEXT_PLAIN_VALUE, "content".getBytes());

        /***
         * Execution stage.
         ***/

        // execute the actual createNewTutorial() method.
        Tutorial newTutorial = this.tutorialService.createNewTutorial(tutorialDto, multipartFile);

        /***
         * Assertion stage.
         ***/

        // make sure that the createdOn and updateOn fields contain an Instant instance.
        MatcherAssert.assertThat(newTutorial.getCreatedOn(), instanceOf(Instant.class));
        MatcherAssert.assertThat(newTutorial.getUpdatedOn(), instanceOf(Instant.class));

        // make sure that the multipart file got a correct storing filename.
        MatcherAssert.assertThat(newTutorial.getImgName(), containsString("file.txt"));

        // make sure that the author of the new tutorial is a user instance.
        MatcherAssert.assertThat(newTutorial.getUser(), instanceOf(User.class));
    }
}