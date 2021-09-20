package com.alessandraipiranga.backend.model;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "player")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {
    @Override
    public int hashCode() {
        return Objects.hash(id, name, number);
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "playerName", nullable = false, unique = true)
    private String name;

    @Column(name = "playerResult")
    private int number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return number == that.number && id.equals(that.id) && name.equals(that.name);
    }
}
