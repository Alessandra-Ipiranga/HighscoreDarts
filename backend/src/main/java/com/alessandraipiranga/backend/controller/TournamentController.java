package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.api.Group;
import com.alessandraipiranga.backend.api.Match;
import com.alessandraipiranga.backend.api.Player;
import com.alessandraipiranga.backend.api.Round;
import com.alessandraipiranga.backend.api.Tournament;
import com.alessandraipiranga.backend.model.GroupEntity;
import com.alessandraipiranga.backend.model.MatchEntity;
import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.model.RoundEntity;
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
import java.util.LinkedHashSet;
import java.util.Set;

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
public class TournamentController {

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

    private Tournament map(TournamentEntity tournamentEntity) {
        Tournament tournament = new Tournament();
        tournament.setId(tournamentEntity.getTournamentId());
        tournament.setRounds(tournamentEntity.getRounds());
        tournament.setStatus(tournamentEntity.getStatus());

        Set<GroupEntity> groups = tournamentEntity.getGroups();
        groups.stream()
                .map(this::map)
                .forEach(tournament::addGroup);

        return tournament;
    }

    private Group map(GroupEntity groupEntity) {
        Group group = new Group();
        group.setName(groupEntity.getName());

        Set<PlayerEntity> playerEntities = groupEntity.getPlayers();
        for (PlayerEntity playerEntity : playerEntities) {
            Player player = map(playerEntity);
            group.addPlayer(player);
        }

        Set<MatchEntity> matches = groupEntity.getMatches();
        for (MatchEntity matchEntity : matches) {
            Match match = map(matchEntity);
            group.addMatch(match);
        }
        return group;
    }

    private Player map(PlayerEntity playerEntity) {
        Player player = new Player();
        player.setId(playerEntity.getId());
        player.setName(playerEntity.getName());
        return player;
    }

    private Match map(MatchEntity matchEntity) {
        Match match = new Match();
        match.setId(matchEntity.getId());
        match.setPlayer1Id(matchEntity.getPlayer1().getId());
        match.setPlayer2Id(matchEntity.getPlayer2().getId());

        Set<Round> rounds = map(matchEntity.getRounds());
        match.setRounds(rounds);
        return match;
    }

    private Set<Round> map(Set<RoundEntity> roundEntities) {
        Set<Round> rounds = new LinkedHashSet<>();
        for (RoundEntity roundEntity : roundEntities) {
            Round round = new Round();
            round.setNumber(roundEntity.getNumber());
            round.setPlayer1Score(roundEntity.getPlayer1Score());
            round.setPlayer2Score(roundEntity.getPlayer2Score());
            rounds.add(round);
        }
        return rounds;
    }
}
