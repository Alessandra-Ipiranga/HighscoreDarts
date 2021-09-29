package com.alessandraipiranga.backend.repo;

import com.alessandraipiranga.backend.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}
