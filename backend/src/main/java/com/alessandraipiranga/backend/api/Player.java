package com.alessandraipiranga.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import static io.swagger.annotations.ApiModelProperty.AccessMode;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, notes = "The id of the player")
    private Long id;

    @ApiModelProperty(required = true, example = "Max Muster", notes = "The name of the player")
    private String name;

    @ApiModelProperty(notes = "The score of the player")
    private Integer score;

    public String getName() {
        return name != null ? name.trim() : null;
    }
}
