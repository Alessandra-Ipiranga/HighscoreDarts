package com.alessandraipiranga.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hs_round")
@Getter
@Setter
public class RoundEntity {

    @Id
    @GeneratedValue
    @Column(name = "round_id")
    private Long id;

    @Column(name = "round_number", nullable = false)
    private int number;

    @Column(name = "round_score_1")
    private Integer player1Score = 0;

    @Column(name = "round_score_2")
    private Integer player2Score = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoundEntity that = (RoundEntity) o;
        if (id != null && that.id != null) {
            return new EqualsBuilder().append(id, that.id).isEquals();
        }
        return new EqualsBuilder().append(number, that.number).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(number).toHashCode();
    }
}
