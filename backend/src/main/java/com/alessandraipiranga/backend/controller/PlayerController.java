package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.model.PlayerEntity;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = PlayerController.USER_CONTROLLER_TAG, description = "Provides CRUD operations for an User")
@Api(
        tags = PlayerController.USER_CONTROLLER_TAG
)
@RequestMapping("/user")
@RestController
public class PlayerController {

    public static final String USER_CONTROLLER_TAG = "Player";

    @GetMapping(value = "/hello")
    public PlayerEntity ola() {
        return new PlayerEntity(12L, "Max", 23);
    }

    @PostMapping(value = "/hello")
    public PlayerEntity ola1() {
        return new PlayerEntity(12L, "Max", 23);
    }
}
