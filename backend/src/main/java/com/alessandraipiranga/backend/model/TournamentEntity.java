package com.alessandraipiranga.backend.model;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "hs_tournament")
public class TournamentEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id")
    private final Set<GroupEntity> groups = new LinkedHashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tournament_id", nullable = false, unique = true)
    private String tournamentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tournament_status", nullable = false)
    private TournamentStatus status;

    @Column(name = "tournament_rounds", nullable = false)
    private int rounds;

    public void addGroup(GroupEntity groupEntity) {
        groups.add(groupEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TournamentEntity that = (TournamentEntity) o;

        return new EqualsBuilder().append(tournamentId, that.tournamentId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tournamentId).toHashCode();
    }
}
