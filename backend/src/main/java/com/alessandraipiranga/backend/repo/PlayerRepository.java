package com.alessandraipiranga.backend.repo;

import com.alessandraipiranga.backend.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByName(String name);
}
