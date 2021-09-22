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
@Table(name = "hs_tournament")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TournamentEntity {

    @Id
    @Column(name = "tournament_id", nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id")
    private final Set<GroupEntity> groups = new HashSet<>();

    @Column(name= "tournament_status", nullable = false)
    private TournamentStatus status;

    @Column(name= "tournament_rounds", nullable = false)
    private int rounds;

}
