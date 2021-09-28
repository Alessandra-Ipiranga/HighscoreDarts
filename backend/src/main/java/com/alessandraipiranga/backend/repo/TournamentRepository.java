package com.alessandraipiranga.backend.repo;

import com.alessandraipiranga.backend.model.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<TournamentEntity, String> {
}
