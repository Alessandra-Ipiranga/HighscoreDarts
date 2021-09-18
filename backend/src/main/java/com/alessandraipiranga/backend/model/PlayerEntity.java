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
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "playerName", nullable = false, unique = true)
    private String name;
    @Column(name = "playerResult", nullable = false)
    private int number;
}
