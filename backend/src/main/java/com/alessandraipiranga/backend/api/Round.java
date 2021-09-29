package com.alessandraipiranga.backend.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Round {

    @ApiModelProperty(notes = "Score of the first player")
    private Integer player1Score;

    @ApiModelProperty(notes = "Score of the second player")
    private Integer player2Score;

    private int number;
}
