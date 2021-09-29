package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.GroupEntity;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.model.TournamentStatus;
import com.alessandraipiranga.backend.repo.TournamentRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static com.alessandraipiranga.backend.model.TournamentStatus.OPEN;

@Service
public class TournamentService {

    public final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public TournamentEntity find(String tournamentId) {
        Optional<TournamentEntity> tournamentEntityOptional = tournamentRepository.findByTournamentId(tournamentId);
        if (tournamentEntityOptional.isPresent()) {
            return tournamentEntityOptional.get();
        }
        throw new EntityNotFoundException("Tournament id=%s not found".formatted(tournamentId));
    }

    public TournamentEntity createTournament(int rounds, int groups) {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setTournamentId(createTournamentId());
        tournamentEntity.setStatus(OPEN);
        tournamentEntity.setRounds(rounds);

        for (int i = 1; i <= groups; i++) {
            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setName(String.valueOf(i));
            tournamentEntity.addGroup(groupEntity);
        }
        return tournamentRepository.save(tournamentEntity);
    }

    public TournamentEntity save(TournamentEntity tournament) {
        return tournamentRepository.save(tournament);
    }

    public TournamentEntity start(String tournamentId) {
        TournamentEntity tournamentEntity = find(tournamentId);
        if (TournamentStatus.OPEN.equals(tournamentEntity.getStatus())) {
            tournamentEntity.setStatus(TournamentStatus.STARTED);
            return save(tournamentEntity);
        }
        if (TournamentStatus.STARTED.equals(tournamentEntity.getStatus())) {
            return tournamentEntity;
        }
        throw new IllegalArgumentException(String.format(
                "Unable to start tournament id=%s tournament in state=%s",
                tournamentEntity.getTournamentId(), tournamentEntity.getStatus()));
    }

    private String createTournamentId() {
        String tournamentId;

        Optional<TournamentEntity> tournamentByIdOpt;
        do {
            // ensure tournament id is unique
            tournamentId = RandomStringUtils.randomAlphanumeric(6);
            tournamentByIdOpt = tournamentRepository.findByTournamentId(tournamentId);

        } while (tournamentByIdOpt.isPresent());

        return tournamentId;
    }
}
