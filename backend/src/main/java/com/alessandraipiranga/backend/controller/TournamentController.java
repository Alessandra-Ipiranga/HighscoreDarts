package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.api.Tournament;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.service.TournamentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = TournamentController.TOURNAMENT_CONTROLLER_TAG,
        description = "Provides CR operations for a Tournament")
@Api(tags = TournamentController.TOURNAMENT_CONTROLLER_TAG)
@CrossOrigin
@RestController
public class TournamentController extends ControllerBase {

    public static final String TOURNAMENT_CONTROLLER_TAG = "Tournament";
    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping(
            value = "/tournament/{id}",
            produces = APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Tournament found"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Tournament not found")
    })
    public ResponseEntity<Tournament> findTournament(@PathVariable String id) {
        TournamentEntity tournamentEntity = tournamentService.find(id);

        Tournament tournament = map(tournamentEntity);
        return ok(tournament);
    }

    @PutMapping(
            value = "/tournament/{id}/start",
            produces = APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Tournament found"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "Tournament cannot be started"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Tournament not found"),
            @ApiResponse(code = SC_CONFLICT, message = "Tournament cannot be started")
    })
    public ResponseEntity<Tournament> startTournament(@PathVariable String id) {
        TournamentEntity tournamentEntity = tournamentService.start(id);

        Tournament tournament = map(tournamentEntity);
        return ok(tournament);
    }

    @PutMapping(
            value = "/tournament/{id}/finish",
            produces = APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Tournament found"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "Tournament cannot be finish"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Tournament not found"),
            @ApiResponse(code = SC_CONFLICT, message = "Tournament cannot be finish")
    })
    public ResponseEntity<Tournament> finishTournament(@PathVariable String id) {
        TournamentEntity tournamentEntity = tournamentService.finish(id);

        Tournament tournament = map(tournamentEntity);
        return ok(tournament);
    }

    @PostMapping(
            value = "/tournament/rounds/{rounds}/groups/{groups}",
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = SC_CREATED, message = "Tournament created")
    })
    public ResponseEntity<Tournament> createTournament(@PathVariable int rounds, @PathVariable int groups) {
        TournamentEntity createdTournamentEntity = tournamentService.createTournament(rounds, groups);

        Tournament createdTournament = map(createdTournamentEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/tournament/%s".formatted(createdTournament.getId()))
                .build()
                .toUri();
        return ResponseEntity.created(location).body(createdTournament);
    }
}
