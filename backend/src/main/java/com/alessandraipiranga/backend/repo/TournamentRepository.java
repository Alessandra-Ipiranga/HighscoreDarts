package com.alessandraipiranga.backend.repo;

import com.alessandraipiranga.backend.model.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TournamentRepository extends JpaRepository<TournamentEntity, String> {

    Optional<TournamentEntity> findByTournamentId(String tournamentId);

    @Query("SELECT t FROM TournamentEntity t LEFT JOIN t.groups g LEFT JOIN g.players p WHERE p.id = :playerId")
    Optional<TournamentEntity> findByPlayerId(Long playerId);

    @Query("SELECT t FROM TournamentEntity t LEFT JOIN t.groups g LEFT JOIN g.matches m WHERE m.id = :matchId")
    Optional<TournamentEntity> findByMatchId(Long matchId);
}
