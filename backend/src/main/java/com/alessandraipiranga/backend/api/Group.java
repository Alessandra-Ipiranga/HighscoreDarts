package com.alessandraipiranga.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group {

    private String name;

    private Set<Player> players;

    private Set<Match> matches;

    public void addPlayer(Player player) {
        if (players == null) {
            players = new LinkedHashSet<>();
        }
        players.add(player);
    }

    public void addMatch(Match match) {
        if (matches == null) {
            matches = new LinkedHashSet<>();
        }
        matches.add(match);
    }
}
