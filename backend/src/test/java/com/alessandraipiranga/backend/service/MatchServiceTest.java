package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.MatchEntity;
import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.repo.MatchRepository;
import com.alessandraipiranga.backend.repo.TournamentRepository;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class MatchServiceTest {

    @Test
    public void testCreateMatches() {
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        player1.setName("A");

        PlayerEntity player2 = new PlayerEntity();
        player2.setId(2L);
        player2.setName("B");

        PlayerEntity player3 = new PlayerEntity();
        player3.setId(3L);
        player3.setName("C");

        PlayerEntity player4 = new PlayerEntity();
        player4.setId(4L);
        player4.setName("D");

        Set<PlayerEntity> players = Set.of(player1, player2, player3, player4);
        assertEquals(4, players.size());

        MatchRepository matchRepository = mock(MatchRepository.class);
        TournamentRepository tournamentRepository = mock(TournamentRepository.class);
        MatchService matchService = new MatchService(matchRepository, tournamentRepository);

        Set<MatchEntity> matches = matchService.createMatches(players, 1);
        assertEquals(6, matches.size());

        Set<MatchEntity> expectedMatches = new HashSet<>();
        expectedMatches.add(createMatch(player1, player2));
        expectedMatches.add(createMatch(player1, player3));
        expectedMatches.add(createMatch(player1, player4));
        expectedMatches.add(createMatch(player2, player3));
        expectedMatches.add(createMatch(player2, player4));
        expectedMatches.add(createMatch(player3, player4));
        assertTrue(expectedMatches.containsAll(matches));
    }

    @Test
    public void testCreateMatch() {
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        player1.setName("A");

        PlayerEntity player2 = new PlayerEntity();
        player2.setId(2L);
        player2.setName("B");

        Set<PlayerEntity> players = Set.of(player1, player2);
        assertEquals(2, players.size());

        MatchRepository matchRepository = mock(MatchRepository.class);
        TournamentRepository tournamentRepository = mock(TournamentRepository.class);
        MatchService matchService = new MatchService(matchRepository, tournamentRepository);

        Set<MatchEntity> matches = matchService.createMatches(players, 1);
        assertEquals(1, matches.size());

        Set<MatchEntity> expectedMatches = new HashSet<>();
        expectedMatches.add(createMatch(player1, player2));
        assertTrue(expectedMatches.containsAll(matches));
    }

    private MatchEntity createMatch(PlayerEntity player1, PlayerEntity player2) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setPlayer1(player1);
        matchEntity.setPlayer2(player2);
        return matchEntity;
    }
}
