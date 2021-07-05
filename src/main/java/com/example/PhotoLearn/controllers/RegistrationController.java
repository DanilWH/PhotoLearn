package com.example.PhotoLearn.controllers;

import com.example.PhotoLearn.dto.CaptchaResponseDto;
import com.example.PhotoLearn.dto.UserDto;
import com.example.PhotoLearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDto", new UserDto());
        
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addNewUser(
            @Valid UserDto userDto,
            BindingResult bindingResult,
            @RequestParam(name="g-recaptcha-response") String gRecaptchaResponse,
            Model model
    ) {
        String url = String.format(CAPTCHA_URL, this.recaptchaSecret, gRecaptchaResponse);
        CaptchaResponseDto response = this.restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess() || bindingResult.hasErrors()) {
            System.out.println(response.getErrorCodes());
            model.addAttribute("captchaError", "Fill captcha!");
            model.addAttribute(userDto);
            return "registration";
        }
        
        this.userService.registerNewUserAccount(userDto); 
        
        return "redirect:/";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(
            @PathVariable String code,
            Model model
    ) {
        boolean isActivated = this.userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User has been successfully activated!");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "User has not been activated!");
        }

        return "login";
    }

}
