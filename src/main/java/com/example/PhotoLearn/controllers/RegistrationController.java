package com.example.PhotoLearn.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.PhotoLearn.services.IUserService;
import com.example.PhotoLearn.web.dto.UserDto;

@Controller
public class RegistrationController {
    
    @Autowired
    private IUserService userService;
    
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

}
