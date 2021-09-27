package com.alessandraipiranga.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tournament {

    private String id;

    private int groups;

    private int rounds;
}

