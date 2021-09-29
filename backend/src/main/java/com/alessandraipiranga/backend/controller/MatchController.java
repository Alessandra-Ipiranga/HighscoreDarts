package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.api.MatchRequest;
import com.alessandraipiranga.backend.api.Tournament;
import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.service.MatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = MatchController.MATCH_CONTROLLER_TAG,
        description = "Provide adding a match result")
@Api(tags = MatchController.MATCH_CONTROLLER_TAG)
@CrossOrigin
@RestController
public class MatchController {

    public static final String MATCH_CONTROLLER_TAG = "Match";

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PutMapping(
            value = "/match/{matchId}/round/{round}/",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = SC_CREATED, message = "Round created"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Tournament not found")
    })
    public ResponseEntity<Tournament> addMatch(@PathVariable String matchId, @PathVariable int round,
                                               @RequestBody MatchRequest matchRequest) {
        Long id = Long.valueOf(matchId);

        PlayerEntity player1 = findPlayer(id, matchRequest.getPlayer1Id());
        PlayerEntity player2 = findPlayer(id, matchRequest.getPlayer2Id());

        matchService.addRound(id, round,
                player1, matchRequest.getPlayer1Score(),
                player2, matchRequest.getPlayer2Score());

        return ResponseEntity.ok().build();
    }

    private PlayerEntity findPlayer(Long matchId, Long playerId) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(playerId);

        return matchService.find(matchId, playerEntity);
    }
}
