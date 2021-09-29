package com.alessandraipiranga.backend.api;

import com.alessandraipiranga.backend.model.TournamentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

import static io.swagger.annotations.ApiModelProperty.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tournament {

    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, example = "ZTg2pQ", notes = "Generated id")
    private String id;

    @ApiModelProperty(required = true, example = "2", notes = "Number of groups to play (How many teams)")
    private int groups;

    @ApiModelProperty(required = true, example = "1", notes = "Number of rounds to play (Each player against anybody in team)")
    private int rounds;

    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, notes = "Status of the tournament")
    private TournamentStatus status;

    private Set<Team> teams;

    public int getGroups() {
        return teams == null ? groups : teams.size();
    }

    public void addTeam(Team team) {
        if (teams == null) {
            teams = new LinkedHashSet<>();
        }
        teams.add(team);
    }
}

