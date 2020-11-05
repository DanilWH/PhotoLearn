package com.example.PhotoLearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PhotoLearn.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
