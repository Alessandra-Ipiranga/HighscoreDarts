package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.GroupEntity;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.repo.TournamentRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.alessandraipiranga.backend.model.TournamentStatus.OPEN;

@Service
public class TournamentService {

    public final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public Optional<TournamentEntity> find(String tournamentId) {
        return tournamentRepository.findByTournamentId(tournamentId);
    }

    public TournamentEntity createTournament(int groups, int rounds) {
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

    private String createTournamentId() {
        String tournamentId;

        Optional<TournamentEntity> tournamentByIdOpt;
        do {
            // ensire
            tournamentId = RandomStringUtils.randomAlphanumeric(6);
            tournamentByIdOpt = find(tournamentId);

        } while (tournamentByIdOpt.isPresent());

        return tournamentId;
    }
}
