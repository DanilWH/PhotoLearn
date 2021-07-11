package com.example.PhotoLearn.services;

import com.example.PhotoLearn.dto.UserDto;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.repositories.UserRepository;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EmailSenderService emailSenderService;

    @Test
    public void registerNewUserAccountTest() {
        // mock the userRepository.save() method.
        Mockito.when(this.userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        UserDto userDto = new UserDto();
        userDto.setEmail("danil-lomakin-02@mail.ru");


        User user = this.userService.registerNewUserAccount(userDto);

        // make sure that an activation code has been set.
        Assert.assertNotNull(user.getActivationCode());
        // make sure that the new user has the only "STUDENT" role.
        MatcherAssert.assertThat(user.getUserRoles(), equalTo(Collections.singleton(UserRoles.ROLE_STUDENT)));

        // make sure that the userRepository.save() method has been called one time.
        Mockito.verify(this.userRepository, Mockito.times(1)).save(user);
        // make sure that the email has been sent.
        Mockito.verify(this.emailSenderService, Mockito.times(1))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    @Test
    public void activateUserTest() {
        // create a new user with its unique activation code.
        User user = new User();
        user.setActivationCode("activation-code");

        // mock the userRepository.findByActivationCode() method returning the created user above.
        Mockito.when(this.userRepository.findByActivationCode("activation-code")).thenReturn(user);

        boolean isUserActivated = this.userService.activateUser("activation-code");

        // make sure that the method has been executed successfully.
        Assert.assertTrue(isUserActivated);
        // make sure that the activation code has been reset to the null value.
        Assert.assertNull(user.getActivationCode());

        // make sure that the userRepository.save() method has been executed once.
        Mockito.verify(this.userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = this.userService.activateUser("not-existing-activation-code");

        // make sure that the activation method hasn't been executed because
        // the user with that particular activation code does not exist.
        Assert.assertFalse(isUserActivated);

        // make sure that the user hasn't been updated in the database.
        Mockito.verify(this.userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }
}