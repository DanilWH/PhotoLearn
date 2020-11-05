package com.example.PhotoLearn.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PhotoLearn.dto.PostDto;
import com.example.PhotoLearn.models.Post;
import com.example.PhotoLearn.models.User;
import com.example.PhotoLearn.repositories.PostRepository;

@Service
public class PostService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;
    
    public void createNewPost(PostDto postDto) {
        Post post = new Post();
        
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreatedOn(Instant.now());
        User user = this.userService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException("No user logged in"));
        post.setUser(user);
        
        this.postRepository.save(post);
    }

}
