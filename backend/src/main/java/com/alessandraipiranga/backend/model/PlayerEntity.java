package com.alessandraipiranga.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "player")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue
    @Column(name = "player_name", nullable = false, unique = true)
    private String name;
    @Column(name = "player_result", nullable = false)
    private int number;
}
