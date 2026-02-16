package com.example.demo.DTO;

import com.example.demo.entity.PostUser;

import java.util.List;

public class PostDTO
{
    private String id;

    private String title;

    private String content;

    private PostUserDTO postUserDTO;

    public PostUserDTO getPostUserDTO() {
        return postUserDTO;
    }

    public void setPostUserDTO(PostUserDTO postUserDTO) {
        this.postUserDTO = postUserDTO;
    }

    public PostDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
