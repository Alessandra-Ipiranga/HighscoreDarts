package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.api.Match;
import com.alessandraipiranga.backend.api.MatchRequest;
import com.alessandraipiranga.backend.model.MatchEntity;
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

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = MatchController.MATCH_CONTROLLER_TAG,
        description = "Provide adding a match result")
@Api(tags = MatchController.MATCH_CONTROLLER_TAG)
@CrossOrigin
@RestController
public class MatchController extends ControllerBase {

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
            @ApiResponse(code = SC_BAD_REQUEST, message = "Players or rounds invalid or invalid score values"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Match or tournament or not found"),
            @ApiResponse(code = SC_CONFLICT, message = "Tournament not in status STARTED")
    })
    public ResponseEntity<Match> addMatch(@PathVariable String matchId, @PathVariable int round,
                                          @RequestBody MatchRequest matchRequest) {
        Long id = Long.valueOf(matchId);

        MatchEntity matchEntity = matchService.addRound(id, round, matchRequest.getPlayer1Score(), matchRequest.getPlayer2Score());

        Match match = map(matchEntity);
        return ResponseEntity.ok(match);
    }
}
