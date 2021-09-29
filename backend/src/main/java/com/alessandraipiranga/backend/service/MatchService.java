package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.MatchEntity;
import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.model.RoundEntity;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.model.TournamentStatus;
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

    public MatchEntity addRound(Long id, int round, int scorePlayer1, int scorePlayer2) {
        MatchEntity matchEntity = find(id);

        checkScore(scorePlayer1);
        checkScore(scorePlayer2);

        Optional<TournamentEntity> tournamentEntityOptional = tournamentRepository.findByMatchId(matchEntity.getId());
        if (tournamentEntityOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format("Tournament not found for match id=%d", matchEntity.getId()));
        }
        TournamentEntity tournamentEntity = tournamentEntityOptional.get();

        if (!TournamentStatus.STARTED.equals(tournamentEntity.getStatus())) {
            throw new IllegalStateException(String.format(
                    "Tournament not in state %s but in state %s",
                    TournamentStatus.STARTED, tournamentEntity.getStatus()));
        }

        int tournamentEntityRounds = tournamentEntity.getRounds();
        if (round < 0 || round > tournamentEntityRounds) {
            throw new IllegalArgumentException(
                    String.format("Round must in between 1 and %d but was %d", tournamentEntityRounds, round));
        }

        Set<RoundEntity> roundEntities = matchEntity.getRounds();
        for (RoundEntity roundEntity : roundEntities) {
            if (roundEntity.getNumber() == round) {
                roundEntity.setPlayer1Score(scorePlayer1);
                roundEntity.setPlayer2Score(scorePlayer2);
            }
        }
        return matchRepository.save(matchEntity);
    }

    private void checkScore(int score) {
        if (score < 0 || score > 180) {
            throw new IllegalArgumentException(String.format("Score must in between 0 and 180 but was %d", score));
        }
    }

    private int random(int playerSize, int invalid) {
        int random;
        do {
            random = this.random.nextInt(playerSize);
        } while (random == invalid);

        return random;
    }
}
