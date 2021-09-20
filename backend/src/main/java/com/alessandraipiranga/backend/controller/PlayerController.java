package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.api.Player;
import com.alessandraipiranga.backend.model.PlayerEntity;

import com.alessandraipiranga.backend.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = PlayerController.USER_CONTROLLER_TAG, description = "Provides CRUD operations for an User")
@Api(
        tags = PlayerController.USER_CONTROLLER_TAG
)
@RequestMapping("/dart")
@RestController
public class PlayerController {

    public static final String USER_CONTROLLER_TAG = "Player";
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_BAD_REQUEST, message = "Unable to create User with blank name"),
            @ApiResponse(code = SC_CONFLICT, message = "Unable to create User, user already exists")
    })
    public ResponseEntity<Player> find(@PathVariable String name) {
        Optional<PlayerEntity> userEntityOptional = playerService.find(name);
        if (userEntityOptional.isPresent()) {
            PlayerEntity playerEntity = userEntityOptional.get();
            Player user = map(playerEntity);
            return ok(user);
        }
        return notFound().build();
    }

    private Player map(PlayerEntity playerEntity) {
        return Player.builder()
                .name(playerEntity.getName())
                .build();
    }

    @PostMapping(value = "/{name}")
    @ApiResponses(value = {
            @ApiResponse(code = SC_BAD_REQUEST, message = "Unable to create User with blank name"),
            @ApiResponse(code = SC_CONFLICT, message = "Unable to create User, user already exists")
    })
    public ResponseEntity<Player> create(@PathVariable String name, @RequestBody String player) {
        PlayerEntity playerEntity = PlayerEntity.builder().name(name).build();
        PlayerEntity createdUserEntity = playerService.create(playerEntity);

        Player createdPlayer = map(createdUserEntity);

        return ok(createdPlayer);
    }

    private PlayerEntity map(Player player) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(player.getName());
        return playerEntity;
    }

}
