package com.example.demo.service.impl;

import com.example.demo.entity.PostUser;
import com.example.demo.DTO.PostUserDTO;
import com.example.demo.repository.PostUserRepository;
import com.example.demo.service.PostUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostUserServiceImpl implements PostUserService
{
    ModelMapper modelMapper;
    PostUserRepository postUserRepository;

    PasswordEncoder passwordEncoder;

    public PostUserServiceImpl(ModelMapper modelMapper,  PostUserRepository postUserRepository , PasswordEncoder passwordEncoder)
    {
        this.modelMapper = modelMapper;
        this.postUserRepository = postUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PostUserDTO createPostUser(PostUserDTO postUserDTO)
    {
        System.out.println(postUserDTO.getUserFullName());
        String id =  UUID.randomUUID().toString();
        PostUser map = modelMapper.map(postUserDTO, PostUser.class);
        map.setId(id);
        map.setPassword(passwordEncoder.encode(postUserDTO.getPassword())); // ✅
        PostUser save = postUserRepository.save(map);
        return modelMapper.map(save, PostUserDTO.class);
    }

    @Override
    public List<PostUserDTO> getAllPOstUser() {
        List<PostUser> all = postUserRepository.findAll();

        List<PostUserDTO> collect = all.stream().map(e -> modelMapper.map(e, PostUserDTO.class)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public PostUserDTO getPostUserById(String id)
    {
        Optional<PostUser> byId = postUserRepository.findById(id);

        PostUserDTO map = modelMapper.map(byId, PostUserDTO.class);

        return map;
    }

    @Override
    public Boolean deletePostUserById(String id)
    {
           Optional<PostUser> byId = postUserRepository.findById(id);
        if(byId.isPresent()) {
            postUserRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PostUserDTO updatePostUserDetails(PostUserDTO postUserDTO)
    {
        Optional<PostUser> byId = postUserRepository.findById(postUserDTO.getId());
        PostUser postUser = byId.get();
        postUser.setUserFullName(postUserDTO.getUserFullName());
        postUser.setEmail(postUserDTO.getEmail());
        postUser.setMobile(postUserDTO.getMobile());
        postUser.setId(postUser.getId());

        PostUser save = postUserRepository.save(postUser);
        return modelMapper.map(save, PostUserDTO.class);
    }


}
