package com.alessandraipiranga.backend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "hs_tournament")
@AllArgsConstructor
@NoArgsConstructor
public class TournamentEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id")
    private final Set<GroupEntity> groups = new HashSet<>();
    @Id
    @Column(name = "tournament_id", nullable = false, unique = true)
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tournament_status", nullable = false)
    private TournamentStatus status;

    @Column(name = "tournament_rounds", nullable = false)
    private int rounds;

    public void addGroup(GroupEntity groupEntity) {
        groups.add(groupEntity);
    }
}
