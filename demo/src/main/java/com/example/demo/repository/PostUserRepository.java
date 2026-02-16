package com.example.demo.repository;

import com.example.demo.entity.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostUserRepository extends JpaRepository<PostUser , String>
{
//    Optional<PostUser> getPostUser(String user);
}
