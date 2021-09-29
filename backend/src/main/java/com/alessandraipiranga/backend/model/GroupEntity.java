package com.alessandraipiranga.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


@Entity
@Table(name = "hs_group")
@Getter
@Setter
public class GroupEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    private final Set<PlayerEntity> players = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    private final Set<MatchEntity> matches = new LinkedHashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String name;

    @OneToOne
    private PlayerEntity winner;

    private Integer winnerScore;

    public void addPlayer(PlayerEntity playerEntity) {
        players.add(playerEntity);
    }

    public void addMatches(Collection<MatchEntity> matchEntities) {
        matches.addAll(matchEntities);
    }

    @Transient
    public void selectWinner() {
        Map<PlayerEntity, Integer> playerScores = new HashMap<>();
        for (MatchEntity matchEntity : getMatches()) {
            Integer player1TotalScore =
                    playerScores.computeIfAbsent(matchEntity.getPlayer1(), key -> 0);

            player1TotalScore += matchEntity.getPlayer1TotalScore();
            playerScores.put(matchEntity.getPlayer1(), player1TotalScore);

            Integer player2TotalScore =
                    playerScores.computeIfAbsent(matchEntity.getPlayer2(), key -> 0);

            player2TotalScore += matchEntity.getPlayer2TotalScore();
            playerScores.put(matchEntity.getPlayer2(), player2TotalScore);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GroupEntity that = (GroupEntity) o;
        if (id != null && that.id != null) {
            return new EqualsBuilder().append(id, that.id).isEquals();
        }
        return new EqualsBuilder().append(name, that.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(id).toHashCode();
    }
}
