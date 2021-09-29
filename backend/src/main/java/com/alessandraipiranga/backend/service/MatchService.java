package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.api.Round;
import com.alessandraipiranga.backend.model.MatchEntity;
import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.model.RoundEntity;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.repo.MatchRepository;
import com.alessandraipiranga.backend.repo.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.apache.commons.math3.util.CombinatoricsUtils.factorial;

@Service
public class MatchService {

    private final Random random = new Random();
    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public MatchService(MatchRepository tournamentRepository, TournamentRepository tournamentRepository1) {
        this.matchRepository = tournamentRepository;
        this.tournamentRepository = tournamentRepository1;
    }

    public PlayerEntity find(Long id, PlayerEntity playerEntity) {
        MatchEntity matchEntity = find(id);

        if (matchEntity.getPlayer1().equals(playerEntity)) {
            return matchEntity.getPlayer1();
        }
        if (matchEntity.getPlayer2().equals(playerEntity)) {
            return matchEntity.getPlayer2();
        }

        throw new IllegalArgumentException(String.format(
                "Match id=%d does not contain player id=%d", id, playerEntity.getId()));
    }

    private MatchEntity find(Long id) {
        Optional<MatchEntity> matchEntityOptional = matchRepository.findById(id);
        if (matchEntityOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format("Match id=%d not found", id));
        }
        return matchEntityOptional.get();
    }

    public Set<MatchEntity> createMatches(Set<PlayerEntity> players, int rounds) {
        if (players.isEmpty()) {
            return Collections.emptySet();
        }

        List<PlayerEntity> playersList = new LinkedList<>(players);
        int playersSize = playersList.size();
        long matchesRequired = getAmountOfMatches(playersSize);

        Set<MatchEntity> matchEntities = new HashSet<>();
        do {
            MatchEntity matchEntity = new MatchEntity();

            int player1Index = random(playersSize, -1);
            matchEntity.setPlayer1(playersList.get(player1Index));

            int player2Index = random(playersSize, player1Index);
            matchEntity.setPlayer2(playersList.get(player2Index));

            matchEntities.add(matchEntity);

        } while (matchEntities.size() < matchesRequired);

        for (MatchEntity matchEntity : matchEntities) {
            for (int i = 1; i <= rounds; i++) {
                RoundEntity roundEntity = new RoundEntity();
                roundEntity.setNumber(i);
                matchEntity.addRound(roundEntity);
            }
        }
        return matchEntities;
    }

    private long getAmountOfMatches(int playersSize) {
        return factorial(playersSize) / (2 * factorial(playersSize - 2));
    }

    public void addRound(Long id, int round, PlayerEntity player1, int scorePlayer1, PlayerEntity player2, int scorePlayer2) {
        MatchEntity matchEntity = find(id);

        Optional<TournamentEntity> tournamentEntityOptional = tournamentRepository.findByMatchId(matchEntity.getId());
        if (tournamentEntityOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format("Tournament not found for match id=%d", matchEntity.getId()));
        }
        TournamentEntity tournamentEntity = tournamentEntityOptional.get();

        int tournamentEntityRounds = tournamentEntity.getRounds();
        if (round < 0 && round > tournamentEntityRounds) {
            throw new IllegalArgumentException("Round must in between 1 and " + tournamentEntityRounds);
        }


//
//        RoundEntity roundEntity = new RoundEntity();
//        roundEntity.setScore(score);


//        matchEntity.addRound();
    }

    private int random(int playerSize, int invalid) {
        int random;
        do {
            random = this.random.nextInt(playerSize);
        } while (random == invalid);

        return random;
    }
}
