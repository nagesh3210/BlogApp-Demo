package com.example.demo.service.impl;
import com.example.demo.DTO.PostDTO;
import com.example.demo.DTO.PostUserDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.PostUser;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.PostUserRepository;
import com.example.demo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    PostUserRepository postUserRepository;
    PostRepository postRepository;
    ModelMapper modelMapper;
    public PostServiceImpl(PostUserRepository postUserRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.postUserRepository = postUserRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PostDTO createPost(PostDTO postDTO, String postId)
    {

        String string = UUID.randomUUID().toString();
        Post map = modelMapper.map(postDTO, Post.class);
        map.setId(string);
        Optional<PostUser> byId = postUserRepository.findById(postId);
        PostUser postUser = byId.get();
        map.setPostUser(postUser);
        Post save = postRepository.save(map);
        PostUser postUser1 = save.getPostUser();
        PostUserDTO map1 = modelMapper.map(postUser1, PostUserDTO.class);
        PostDTO map2 = modelMapper.map(save, PostDTO.class);
        map2.setPostUserDTO(map1);
        return map2;
    }
    @Override
    public boolean deletePost(String postId)
    {
        Optional<Post> byId = postRepository.findById(postId);
        if(byId.isPresent())
        {
            Post post = byId.get();
            postRepository.deleteById(postId);
            return true;
        }
        return  false;
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, String postId)
    {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        Post save = postRepository.save(post);

        PostDTO map = modelMapper.map(save, PostDTO.class);
        PostUser postUser = save.getPostUser();
        map.setPostUserDTO(modelMapper.map(postUser, PostUserDTO.class));

        return map;
    }




    @Override
    public List<PostDTO> getAllPost(String postUserId)
    {
        List<Post> byPostUserId = postRepository.findByPostUser_Id(postUserId);

        List<PostDTO> collect = byPostUserId.stream().map((e) ->
        {
            PostUser postUser = e.getPostUser();
            PostDTO map = modelMapper.map(e, PostDTO.class);
            PostUserDTO map1 = modelMapper.map(postUser, PostUserDTO.class);
            map.setPostUserDTO(map1);
            return map;
        }).collect(Collectors.toList());
        return collect;
    }
}
