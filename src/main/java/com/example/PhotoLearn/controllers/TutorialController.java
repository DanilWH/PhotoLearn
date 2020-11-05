package com.example.PhotoLearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TutorialController {
    
    @GetMapping("/")
    public String index() {
        return "redirect:/tutorials";
    }
    
    @GetMapping("/tutorials")
    public String showTutorials() {
        return null;
    }

}