package com.alessandraipiranga.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Match {

    @ApiModelProperty(required = true, notes = "The id of the match")
    private Long id;

    @ApiModelProperty(required = true, notes = "Amount of rounds the players has been played against each other")
    private Set<Round> rounds;

    @ApiModelProperty(required = true, notes = "The id of the first player")
    private Long player1Id;

    @ApiModelProperty(required = true, notes = "The total score of the first player")
    private Integer player1ScoreTotal;

    @ApiModelProperty(required = true, notes = "The id of the second player")
    private Long player2Id;

    @ApiModelProperty(required = true, notes = "The total score of the second player")
    private Integer player2ScoreTotal;
}
