package com.example.PhotoLearn.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.PhotoLearn.models.UserDto;

@Controller
public class RegistrationController {
    
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
            model.addAttribute("noErrors", false);
            model.addAttribute("userDto", userDto);
            return "registration";
        }
        
        
        
        return "redirect:/";
    }

}
