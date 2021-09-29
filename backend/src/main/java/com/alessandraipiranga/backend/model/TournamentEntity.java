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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "hs_tournament")
public class TournamentEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
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

    @OneToOne
    private PlayerEntity winner;

    private Integer winnerScore;

    public void addGroup(GroupEntity groupEntity) {
        groups.add(groupEntity);
    }

    public void removeGroup(GroupEntity group) {
        groups.removeIf(groupEntity -> groupEntity.equals(group));
    }

    @Transient
    public Optional<GroupEntity> getGroupPlayer(PlayerEntity playerEntity) {
        for (GroupEntity groupEntity : groups) {
            if (groupEntity.getPlayers().contains(playerEntity)) {
                return Optional.of(groupEntity);
            }
        }
        return Optional.empty();
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

    public void selectWinner() {
        Map<PlayerEntity, Integer> playerScores = new HashMap<>();
        for (GroupEntity groupEntity : getGroups()) {
            Integer winnerScore =
                    playerScores.computeIfAbsent(groupEntity.getWinner(), key -> 0);

            winnerScore += groupEntity.getWinnerScore();
            playerScores.put(groupEntity.getWinner(), winnerScore);
        }

        for (Map.Entry<PlayerEntity, Integer> playerScore : playerScores.entrySet()) {
            PlayerEntity winningPlayer = playerScore.getKey();
            Integer winningScore = playerScore.getValue();

            if (getWinnerScore() == null || getWinnerScore() < winningScore) {
                setWinnerScore(winningScore);
                setWinner(winningPlayer);
            }
        }
    }
}
