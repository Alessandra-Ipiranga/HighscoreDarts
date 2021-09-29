package com.alessandraipiranga.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;


@Entity
@Table(name = "hs_match")
@Getter
@Setter
public class MatchEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "match_id")
    private final Set<RoundEntity> rounds = new LinkedHashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "match_id")
    private Long id;

    @ManyToOne(optional = false)
    private PlayerEntity player1;

    @ManyToOne(optional = false)
    private PlayerEntity player2;

    public void addRound(RoundEntity roundEntity) {
        rounds.add(roundEntity);
    }

    public Set<RoundEntity> getRounds() {
        LinkedList<RoundEntity> sortedRounds = new LinkedList<>(rounds);
        Collections.sort(sortedRounds);
        return new LinkedHashSet<>(sortedRounds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MatchEntity that = (MatchEntity) o;
        if (id != null && that.id != null) {
            return new EqualsBuilder().append(id, that.id).isEquals();
        }

        if (player1.equals(that.getPlayer1()) && player2.equals(that.getPlayer2())) {
            return true;
        }
        return player1.equals(that.getPlayer2()) && player2.equals(that.getPlayer1());
    }

    @Override
    public int hashCode() {
        return player1.hashCode() + player2.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("player1", player1)
                .append("player2", player2)
                .toString();
    }
}
