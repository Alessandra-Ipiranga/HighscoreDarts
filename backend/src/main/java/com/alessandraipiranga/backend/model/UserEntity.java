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
    @Column(name = "user_name", nullable = false, unique = true)
    private String name;
    @Column(name = "user_password", nullable = false)
    private String password;
}