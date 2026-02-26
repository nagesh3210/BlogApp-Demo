package com.example.demo.controller;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.PostUserDTO;
import com.example.demo.entity.PostUser;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repository.PostUserRepository;
import com.example.demo.service.PostUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController
{
    @Autowired
    PostUserService postUserService;

    @Autowired
    PostUserRepository postUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/create")
    public ResponseEntity<?> CreateUser(@RequestBody PostUserDTO postUserDTO)
    {
        Optional<PostUser> byEmail = postUserRepository.findByEmail(postUserDTO.getEmail());
        if(byEmail.isPresent())
        {
            return ResponseEntity.ok(
                    Map.of("message", "User With this email is already present"));
        }
        else {
            PostUserDTO postUser = postUserService.createPostUser(postUserDTO);
            return ResponseEntity.ok(
                    Map.of("message", "User is created successfully"));
        }

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }

}
