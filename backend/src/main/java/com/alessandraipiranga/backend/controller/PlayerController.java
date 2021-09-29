package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.api.Player;
import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = PlayerController.PLAYER_CONTROLLER_TAG, description = "Provides CRUD operations for a Player")
@Api(tags = PlayerController.PLAYER_CONTROLLER_TAG)
@RestController
public class PlayerController {

    public static final String PLAYER_CONTROLLER_TAG = "Player";

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(
            value = "/player/{id}",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Player> findPlayer(@PathVariable Long id) {
        PlayerEntity playerEntity = playerService.find(id);
        Player findPlayer = map(playerEntity);
        return ok(findPlayer);
    }

    @PostMapping(
            value = "/player/tournament/{tournamentId}/team/{teamName}",
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = SC_CREATED, message = "Player created"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Tournament or team not found"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "Unable to create Player with blank name"),
            @ApiResponse(code = SC_CONFLICT, message = "Unable to create Player, player already exists")
    })
    public ResponseEntity<Player> create(@RequestBody Player player,
                                         @PathVariable String tournamentId, @PathVariable String teamName) {

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(player.getName());

        PlayerEntity createdPlayerEntity = playerService.create(playerEntity, tournamentId, teamName);

        Player createdPlayer = map(createdPlayerEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/player/%s".formatted(createdPlayer.getId()))
                .build()
                .toUri();
        return ResponseEntity.created(location).body(createdPlayer);
    }

    @PutMapping(
            value = "/player/{id}",
            produces = APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Player updated"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Player not found")
    })
    public ResponseEntity<Player> update(@PathVariable Long id, Player player) {
        String name = player.getName();
        Assert.hasText(name, "Player name must not be blank to update");

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(name);

        PlayerEntity updatedPlayerEntity = playerService.update(id, playerEntity);

        Player updatedPlayer = map(updatedPlayerEntity);
        return ok(updatedPlayer);
    }

    @DeleteMapping(
            value = "/player/{id}",
            produces = APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Player deleted"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Player not found")
    })
    public ResponseEntity<Player> delete(@PathVariable Long id) {
        PlayerEntity playerEntity = playerService.delete(id);

        Player player = map(playerEntity);
        return ok(player);
    }

    private List<Player> map(List<PlayerEntity> playerEntityList) {
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            Player player = map(playerEntity);
            playerList.add(player);

        }
        return (playerList);
    }

    private Player map(PlayerEntity playerEntity) {
        Player player = new Player();
        player.setName(playerEntity.getName());
        player.setId(playerEntity.getId());
        return player;
    }
}
