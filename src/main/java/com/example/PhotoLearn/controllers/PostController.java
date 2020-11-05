package com.example.PhotoLearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    
    @GetMapping("/")
    public String index() {
        return "redirect:/posts";
    }
    
    @GetMapping("/posts")
    public String showPosts() {
        return null;
    }

}
