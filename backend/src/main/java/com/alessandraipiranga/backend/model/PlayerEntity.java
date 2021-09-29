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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "hs_player")
@Getter
@Setter
public class PlayerEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private final Set<RoundEntity> rounds = new LinkedHashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "player_id")
    private Long id;

    @Column(name = "player_name", nullable = false)
    private String name;

    public void addRound(RoundEntity roundEntity) {
        rounds.add(roundEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayerEntity that = (PlayerEntity) o;
        if (id != null && that.id != null) {
            return new EqualsBuilder().append(id, that.id).isEquals();
        }
        return new EqualsBuilder().append(name, that.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).toHashCode();
    }
}

