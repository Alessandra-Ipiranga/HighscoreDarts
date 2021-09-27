package com.alessandraipiranga.backend.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "hs_tournament")
@AllArgsConstructor
@NoArgsConstructor
public class TournamentEntity {

    @Id
    @Column(name = "tournament_id", nullable = false, unique = true)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id")
    private final Set<GroupEntity> groups = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name= "tournament_status", nullable = false)
    private TournamentStatus status;

    @Column(name= "tournament_rounds", nullable = false)
    private int rounds;

    public void addGroup(GroupEntity groupEntity){
        groups.add(groupEntity);
    }
}
