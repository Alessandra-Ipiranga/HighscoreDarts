package com.alessandraipiranga.backend.model;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "groups")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GroupEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private final Set<MatchEntity> match = new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "group_match", nullable = false, unique = true)
    private int number;
}