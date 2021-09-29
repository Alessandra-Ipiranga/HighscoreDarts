package com.alessandraipiranga.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {

    private String name;

    private Set<Player> players;

    public void addPlayer(Player player) {
        if (players == null) {
            players = new LinkedHashSet<>();
        }
        players.add(player);
    }
}
