package com.alessandraipiranga.backend.repo;

import com.alessandraipiranga.backend.model.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TournamentRepository extends JpaRepository<TournamentEntity, String> {

    Optional<TournamentEntity> findByTournamentId(String tournamentId);
}
