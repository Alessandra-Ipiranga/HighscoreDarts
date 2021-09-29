package com.alessandraipiranga.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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

    @Column(name = "round_score", nullable = false)
    private int score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundEntity that = (RoundEntity) o;
        return number == that.number && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }
}
