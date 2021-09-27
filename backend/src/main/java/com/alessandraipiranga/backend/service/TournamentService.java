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

    public Optional<TournamentEntity> findTournamentById(String id) {
        return tournamentRepository.findById(id);
    }

    public TournamentEntity createTournament(int groups, int rounds) {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setId(getNewTournamentId());
        tournamentEntity.setStatus(OPEN);
        tournamentEntity.setRounds(rounds);

        for(int i = 1; i <= groups; i++){
            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setName(String.valueOf(i));
            tournamentEntity.addGroup(groupEntity);
        }

        return tournamentRepository.save(tournamentEntity);
    }

    private String getNewTournamentId(){
        return RandomStringUtils.randomAlphanumeric(6);
    }
}
