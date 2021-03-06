package com.alessandraipiranga.backend.api;

import com.alessandraipiranga.backend.model.TournamentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

import static io.swagger.annotations.ApiModelProperty.AccessMode;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tournament {

    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, example = "ZTg2pQ", notes = "Generated id")
    private String id;

    @ApiModelProperty(required = true, example = "1", notes = "Number of rounds to play (Each player against anybody in group)")
    private int rounds;

    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, notes = "Status of the tournament")
    private TournamentStatus status;

    @ApiModelProperty(notes = "Tournament groups")
    private Set<Group> groups;

    @ApiModelProperty(notes = "Tournament winner")
    private Player winner;

    @ApiModelProperty(notes = "Total score of the tournament winner")
    private Integer winnerScore;

    public void addGroup(Group group) {
        if (groups == null) {
            groups = new LinkedHashSet<>();
        }
        groups.add(group);
    }
}

