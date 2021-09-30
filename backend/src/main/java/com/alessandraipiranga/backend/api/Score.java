package com.alessandraipiranga.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Score {

    @ApiModelProperty(required = true, notes = "The score of the first player in between 0 and 180")
    private Integer player1Score;

    @ApiModelProperty(required = true, notes = "The score of the second player in between 0 and 180")
    private Integer player2Score;
}
