package com.example.demo.controller;

import com.example.demo.DTO.PostUserDTO;
import com.example.demo.service.PostUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostUserController
{
    @Autowired
    PostUserService postUserService;

    //http://localhost:8080/api/post/create
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody PostUserDTO postUserDTO)
    {
        PostUserDTO postUser = postUserService.createPostUser(postUserDTO);
        return new ResponseEntity(postUser,HttpStatus.CREATED);
    }
//http://localhost:8080/api/post/get?id=0611eefc-c31f-4146-9df0-f080ceb87f27
    @GetMapping("/get")
    public ResponseEntity<?> findUserById(@RequestParam String id)
    {
        PostUserDTO postUserById = postUserService.getPostUserById(id);
        return new ResponseEntity<>(postUserById, HttpStatus.OK);
    }

    //http://localhost:8080/api/post/delete/0611eefc-c31f-4146-9df0-f080ceb87f27
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id)
    {
        Boolean b = postUserService.deletePostUserById(id);
        return  new ResponseEntity<>(b ? "User Deleted Sucessfully":"Something went wrong" , HttpStatus.OK);
    }
    //http://localhost:8080/api/post/getall
    @GetMapping("/getall")
    public ResponseEntity<?> getAllById()
    {
        List<PostUserDTO> allPOstUser = postUserService.getAllPOstUser();
        return new ResponseEntity<>(allPOstUser , HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUserData(@RequestBody PostUserDTO postUserDTO)
    {
        PostUserDTO postUserDTO1 = postUserService.updatePostUserDetails(postUserDTO);
        return new ResponseEntity<>(postUserDTO1, HttpStatus.OK);

    }

}
