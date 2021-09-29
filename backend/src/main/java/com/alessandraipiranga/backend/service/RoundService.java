package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.repo.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class RoundService {

    private final PlayerService playerService;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public RoundService(PlayerService playeplayerServiceRepository, TournamentRepository tournamentRepository) {
        this.playerService = playeplayerServiceRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public void addRound(PlayerEntity player, int round, Integer player1Score) {
        PlayerEntity playerEntity = playerService.find(player.getId());

        Optional<TournamentEntity> tournamentEntityOptional = tournamentRepository.findByPlayerId(playerEntity.getId());
        if (tournamentEntityOptional.isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("Unable to find tournament of player id=%d", playerEntity.getId()));
        }
        TournamentEntity tournamentEntity = tournamentEntityOptional.get();

        if (round < 0 || round > tournamentEntity.getRounds()) {
            throw new IllegalArgumentException(String.format(
                    "Tournament is configured to min rounds of 1 and max rounds of %d", tournamentEntity.getRounds()));
        }

    }
}
