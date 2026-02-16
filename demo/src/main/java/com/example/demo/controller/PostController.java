package com.example.demo.controller;


import com.example.demo.DTO.PostDTO;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/v1")
public class PostController
{
   @Autowired
    PostService postService;

   @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO , @RequestParam String postUserId)
    {
        PostDTO post = postService.createPost(postDTO, postUserId);
        return new ResponseEntity<>(post,HttpStatus.CREATED);

    }

    @GetMapping("/getallpost")
    public ResponseEntity<?> getallPostOfUser(@RequestParam String postUserId)
    {
        List<PostDTO> allPost = postService.getAllPost(postUserId);

        return new ResponseEntity<>(allPost,HttpStatus.OK);

    }


}
