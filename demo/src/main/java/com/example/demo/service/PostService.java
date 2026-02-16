package com.example.demo.service;

import com.example.demo.DTO.PostDTO;
import com.example.demo.DTO.PostUserDTO;

import java.util.List;

public interface PostService
{
    public PostDTO createPost(PostDTO postDTO, String postId);

    public boolean deletePost(String postId);

    public PostDTO updatePost(PostDTO postDTO);

    public List<PostDTO> getAllPost(String postUserId);


}


