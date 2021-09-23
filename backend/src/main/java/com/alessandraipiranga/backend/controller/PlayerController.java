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

import java.util.ArrayList;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.ResponseEntity.ok;

@Tag(name = PlayerController.USER_CONTROLLER_TAG, description = "Provides CRUD operations for an Player")
@Api(tags = PlayerController.USER_CONTROLLER_TAG)
@RequestMapping("/dart")
@RestController
public class PlayerController {

    public static final String USER_CONTROLLER_TAG = "Player";
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Player> findPlayer(@PathVariable String name){
        PlayerEntity playerEntity = playerService.findPlayer(name);
        Player findPlayer = map(playerEntity);
        return ok(findPlayer);
    }

    @GetMapping()
    public ResponseEntity<List<Player>> findAllPlayer(){
        List<PlayerEntity> playerEntityList = playerService.findAllPlayer();
        List <Player> playerList = map(playerEntityList);
        return ok(playerList);
    }

    public List<Player> map(List<PlayerEntity> playerEntityList){

        List<Player> playerList = new ArrayList<>();

        for(PlayerEntity playerEntity :playerEntityList){
            Player player = map(playerEntity);
            playerList.add(player);

        }
        return(playerList);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = SC_BAD_REQUEST, message = "Unable to create Player with blank name"),
            @ApiResponse(code = SC_CONFLICT, message = "Unable to create Player, user already exists")
    })
    public ResponseEntity<Player> create(@RequestBody Player player) {
        PlayerEntity playerEntity = PlayerEntity.builder()
                .name(player.getName())
                .build();

        PlayerEntity createdUserEntity = playerService.create(playerEntity);

        Player createdPlayer = map(createdUserEntity);

        return ok(createdPlayer);
    }

    private Player map(PlayerEntity playerEntity) {
        Player player = new Player();
        player.setName(playerEntity.getName());
        return  player;
    }
}
