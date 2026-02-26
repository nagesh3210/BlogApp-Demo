package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PostUser
{
    @Id
    private String id;
    private String userFullName;
    private String email;
    private String mobile;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName)
    {
        this.userFullName = userFullName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    @OneToMany(
            mappedBy = "postUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Post> posts = new ArrayList<>();


}
