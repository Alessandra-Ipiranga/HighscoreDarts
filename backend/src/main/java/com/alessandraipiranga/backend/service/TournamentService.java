package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.GroupEntity;
import com.alessandraipiranga.backend.model.MatchEntity;
import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.model.TournamentStatus;
import com.alessandraipiranga.backend.repo.TournamentRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

import static com.alessandraipiranga.backend.model.TournamentStatus.OPEN;

@Service
public class TournamentService {

    public final TournamentRepository tournamentRepository;
    public final MatchService matchService;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository, MatchService matchService) {
        this.tournamentRepository = tournamentRepository;
        this.matchService = matchService;
    }

    public TournamentEntity find(String tournamentId) {
        Optional<TournamentEntity> tournamentEntityOptional = tournamentRepository.findByTournamentId(tournamentId);
        if (tournamentEntityOptional.isPresent()) {
            return tournamentEntityOptional.get();
        }
        throw new EntityNotFoundException("Tournament id=%s not found".formatted(tournamentId));
    }

    public TournamentEntity createTournament(int rounds, int groups) {
        if (rounds < 1) {
            throw new IllegalArgumentException("A tournament requires at least 1 round to play");
        }
        if (groups < 1) {
            throw new IllegalArgumentException("A tournament requires at least 1 group to assign players to");
        }

        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setTournamentId(createTournamentId());
        tournamentEntity.setStatus(OPEN);
        tournamentEntity.setRounds(rounds);

        for (int i = 1; i <= groups; i++) {
            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setName(String.valueOf(i));
            tournamentEntity.addGroup(groupEntity);
        }
        return tournamentRepository.save(tournamentEntity);
    }

    public TournamentEntity save(TournamentEntity tournament) {
        return tournamentRepository.save(tournament);
    }

    public TournamentEntity start(String tournamentId) {
        TournamentEntity tournamentEntity = find(tournamentId);
        if (TournamentStatus.STARTED.equals(tournamentEntity.getStatus())) {
            // no nothing, tournament already started
            return tournamentEntity;
        }
        if (!TournamentStatus.OPEN.equals(tournamentEntity.getStatus())) {
            throw new IllegalArgumentException(String.format(
                    "Unable to start tournament id=%s tournament in state=%s",
                    tournamentEntity.getTournamentId(), tournamentEntity.getStatus()));
        }

        Set<GroupEntity> groupEntities = tournamentEntity.getGroups();
        for (GroupEntity groupEntity : groupEntities) {
            Set<PlayerEntity> players = groupEntity.getPlayers();
            if (players.isEmpty()) {
                continue;
            }
            if (players.size() < 2) {
                throw new IllegalStateException(
                        String.format("A match requires at least 2 players. Add more players to group name=%s",
                                groupEntity.getName()));
            }

            int rounds = tournamentEntity.getRounds();

            Set<MatchEntity> matches = matchService.createMatches(players, rounds);
            groupEntity.addMatches(matches);
        }

        tournamentEntity.setStatus(TournamentStatus.STARTED);
        return save(tournamentEntity);
    }

    private String createTournamentId() {
        String tournamentId;

        Optional<TournamentEntity> tournamentByIdOpt;
        do {
            // ensure tournament id is unique
            tournamentId = RandomStringUtils.randomAlphanumeric(6);
            tournamentByIdOpt = tournamentRepository.findByTournamentId(tournamentId);

        } while (tournamentByIdOpt.isPresent());

        return tournamentId;
    }
}
