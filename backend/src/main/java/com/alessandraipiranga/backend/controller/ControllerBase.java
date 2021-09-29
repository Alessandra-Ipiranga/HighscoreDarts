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

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class ControllerBase {

    protected Tournament map(TournamentEntity tournamentEntity) {
        Tournament tournament = new Tournament();
        tournament.setId(tournamentEntity.getTournamentId());
        tournament.setRounds(tournamentEntity.getRounds());
        tournament.setStatus(tournamentEntity.getStatus());
        Player winner = map(tournamentEntity.getWinner());
        if (winner != null) {
            tournament.setWinner(winner);
            tournament.setWinnerScore(tournamentEntity.getWinnerScore());
        }

        Set<GroupEntity> groups = tournamentEntity.getGroups();
        groups.stream()
                .map(this::map)
                .forEach(tournament::addGroup);

        return tournament;
    }

    private Group map(GroupEntity groupEntity) {
        Group group = new Group();
        group.setName(groupEntity.getName());

        Player winner = map(groupEntity.getWinner());
        if (winner != null) {
            group.setWinner(winner);
            group.setWinnerScore(groupEntity.getWinnerScore());
        }

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
        if (playerEntity == null) {
            return null;
        }
        Player player = new Player();
        player.setId(playerEntity.getId());
        player.setName(playerEntity.getName());
        return player;
    }

    protected Match map(MatchEntity matchEntity) {
        Match match = new Match();
        match.setId(matchEntity.getId());
        match.setPlayer1Id(matchEntity.getPlayer1().getId());
        match.setPlayer2Id(matchEntity.getPlayer2().getId());
        match.setPlayer1ScoreTotal(matchEntity.getPlayer1TotalScore());
        match.setPlayer2ScoreTotal(matchEntity.getPlayer2TotalScore());

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
