package com.alessandraipiranga.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group {

    @ApiModelProperty(notes = "Name of the group")
    private String name;

    private Set<Player> players;

    private Set<Match> matches;

    @ApiModelProperty(notes = "Group winner")
    private Player winner;

    @ApiModelProperty(notes = "Total score of the group winner")
    private Integer winnerScore;

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
