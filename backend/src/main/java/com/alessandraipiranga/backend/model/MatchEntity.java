package com.alessandraipiranga.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "match")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MatchEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id")
    private final Set<PlayerEntity> player = new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "match_player", nullable = false, unique = true)
    private String name;
    @Column(name = "match_round", nullable = false)
    private int number;
}
