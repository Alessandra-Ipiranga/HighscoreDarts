package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.model.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @GetMapping
    public List<UserEntity> getAllUsers() {
     return null;
    }
}
