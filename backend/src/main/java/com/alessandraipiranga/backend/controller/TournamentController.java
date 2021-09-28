package com.alessandraipiranga.backend.controller;

import com.alessandraipiranga.backend.api.Tournament;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.service.TournamentService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@Tag(name = TournamentController.TOURNAMENT_CONTROLLER_TAG,
        description = "Provides CRUD operations for a Entity")
@Api(tags = TournamentController.TOURNAMENT_CONTROLLER_TAG)
@RequestMapping("/tournament")
@RestController
public class TournamentController {

    public static final String TOURNAMENT_CONTROLLER_TAG = "Tournament";
    private TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping()
    public ResponseEntity<Tournament> findTournament(@PathVariable String id){
        Optional<TournamentEntity> tournamentEntity = tournamentService.findTournamentById(id);
        if (tournamentEntity.isPresent()) {
            Tournament findTournament = map(tournamentEntity.get());
            return ok(findTournament);
        } return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        TournamentEntity createdTournamentEntity = tournamentService
                .createTournament(tournament.getRounds(), tournament.getGroups());
        Tournament createdTournament = map(createdTournamentEntity);
        return ok(createdTournament);
    }

    private Tournament map(TournamentEntity tournamentEntity) {
        Tournament tournament = new Tournament();
        tournament.setId(tournamentEntity.getId());
        return tournament;
    }
}
