package com.example.demo.service;

import com.example.demo.DTO.PostUserDTO;

import java.util.List;

public interface PostUserService
{
    public PostUserDTO createPostUser(PostUserDTO postUserDTO);
    public List<PostUserDTO> getAllPOstUser();
    public PostUserDTO getPostUserById(String id);
    public Boolean deletePostUserById(String id);
    public PostUserDTO updatePostUserDetails(PostUserDTO postUserDTO);
}
