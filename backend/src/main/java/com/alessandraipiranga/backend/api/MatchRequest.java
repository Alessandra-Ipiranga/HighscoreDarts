package com.alessandraipiranga.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchRequest {

    @ApiModelProperty(required = true, notes = "The score of the first player")
    private Integer player1Score;

    @ApiModelProperty(required = true, notes = "The score of the second player")
    private Integer player2Score;
}
