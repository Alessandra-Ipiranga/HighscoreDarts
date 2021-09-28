package com.alessandraipiranga.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "hs_round")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
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
