package com.example.PhotoLearn.controllers;

import com.example.PhotoLearn.dto.UserDto;
import com.example.PhotoLearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDto", new UserDto());
        
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addNewUser(
            @Valid UserDto userDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
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
