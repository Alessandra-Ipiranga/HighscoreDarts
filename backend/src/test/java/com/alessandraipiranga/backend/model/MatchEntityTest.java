package com.alessandraipiranga.backend.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MatchEntityTest {

    @Test
    public void testEquality() {
        PlayerEntity player1 = new PlayerEntity();
        player1.setId(1L);
        player1.setName("A");

        PlayerEntity player2 = new PlayerEntity();
        player2.setId(2L);
        player2.setName("B");

        MatchEntity matchEntity1 = new MatchEntity();
        matchEntity1.setPlayer1(player1);
        matchEntity1.setPlayer2(player2);

        MatchEntity matchEntity2 = new MatchEntity();
        matchEntity2.setPlayer1(player2);
        matchEntity2.setPlayer2(player1);

        assertEquals(matchEntity1.hashCode(), matchEntity2.hashCode());
        assertEquals(matchEntity1, matchEntity2);

        Set<MatchEntity> matches = new HashSet<>();
        matches.add(matchEntity1);
        matches.add(matchEntity2);

        assertEquals(1, matches.size());
    }
}
