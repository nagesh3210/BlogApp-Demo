package com.example.demo.DTO;

public class PostUserDTO
{
    private String id;
    private String userFullName;

    private String email;

    private String mobile;

    public String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

public PostUserDTO()
{}

    public PostUserDTO(String id, String userFullName , String email , String mobile)
    {
        this.email = email;
        this.id=id;
        this.userFullName = userFullName;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
