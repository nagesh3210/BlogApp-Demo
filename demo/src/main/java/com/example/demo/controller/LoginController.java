package com.example.demo.controller;

import com.example.demo.DTO.PostUserDTO;
import com.example.demo.repository.PostUserRepository;
import com.example.demo.service.PostUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController
{
    @Autowired
    PostUserService postUserService;

    @Autowired
    PostUserRepository postUserRepository;


    public ResponseEntity<?> CreateUser(PostUserDTO postUserDTO)
    {
        return null;
    }

}
