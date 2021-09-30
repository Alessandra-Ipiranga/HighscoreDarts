package com.alessandraipiranga.backend.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Round {

    @ApiModelProperty(notes = "Scores of the players in one round")
    private Score score;

    @ApiModelProperty(notes = "Number of the round")
    private int number;
}
