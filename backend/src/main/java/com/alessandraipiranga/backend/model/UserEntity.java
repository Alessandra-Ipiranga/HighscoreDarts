package com.alessandraipiranga.backend.model;
import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "userlogin")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "userName", nullable = false, unique = true)
    private String name;
    @Column(name = "userPassword", nullable = false)
    private String password;
}