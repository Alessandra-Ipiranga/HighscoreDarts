package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.GroupEntity;
import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.model.TournamentEntity;
import com.alessandraipiranga.backend.model.TournamentStatus;
import com.alessandraipiranga.backend.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TournamentService tournamentService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TournamentService tournamentService) {
        this.playerRepository = playerRepository;
        this.tournamentService = tournamentService;
    }

    public PlayerEntity create(PlayerEntity playerEntity, String tournamentId, String groupName) {
        String name = playerEntity.getName();
        if (!hasText(name)) {
            throw new IllegalArgumentException("Name must not be blank to create Player");
        }

        TournamentEntity tournamentEntity = tournamentService.find(tournamentId);
        if (!TournamentStatus.OPEN.equals(tournamentEntity.getStatus())) {
            throw new IllegalArgumentException(String.format(
                    "Adding players to a tournament not allowed in state OPEN, but state is=%s",
                    tournamentEntity.getStatus()));
        }

        checkPlayerNameExists(tournamentEntity, playerEntity.getName());

        for (GroupEntity groupEntity : tournamentEntity.getGroups()) {
            if (groupEntity.getName().equals(groupName)) {
                groupEntity.addPlayer(playerEntity);
            }
        }

        if (tournamentEntity.getGroupPlayer(playerEntity).isEmpty()) {
            throw new EntityNotFoundException("Group name=%s not found".formatted(groupName));
        }

        tournamentEntity = tournamentService.save(tournamentEntity);
        return find(tournamentEntity.getTournamentId(), playerEntity);
    }

    private void checkPlayerNameExists(TournamentEntity tournamentEntity, String playerName) {
        PlayerEntity examplePlayer = new PlayerEntity();
        examplePlayer.setName(playerName);

        Optional<GroupEntity> groupPlayerOpt = tournamentEntity.getGroupPlayer(examplePlayer);
        if (groupPlayerOpt.isPresent()) {
            throw new EntityExistsException(String.format(
                    "Player with name=%s already exists in group %s", playerName, groupPlayerOpt.get().getName()));
        }
    }

    public PlayerEntity find(String tournamentId, PlayerEntity playerEntity) {
        TournamentEntity tournamentEntity = tournamentService.find(tournamentId);

        Optional<GroupEntity> playerGroupOpt = tournamentEntity.getGroupPlayer(playerEntity);
        if (playerGroupOpt.isPresent()) {
            GroupEntity groupEntity = playerGroupOpt.get();

            for (PlayerEntity player : groupEntity.getPlayers()) {
                if (player.equals(playerEntity)) {
                    return player;
                }
            }
        }
        throw new EntityNotFoundException(
                String.format("Player id=%d not found in tournament id=%d",
                        playerEntity.getId(), tournamentEntity.getId()));
    }

    public PlayerEntity find(Long id) {
        Optional<PlayerEntity> playerEntityOptional = playerRepository.findById(id);
        if (playerEntityOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format("Player id=%d not found", id));
        }
        return playerEntityOptional.get();
    }

    public PlayerEntity delete(Long id) {
        PlayerEntity playerEntity = find(id);
        playerRepository.delete(playerEntity);
        return playerEntity;
    }

    public PlayerEntity update(Long id, PlayerEntity player) {
        PlayerEntity playerEntity = find(id);
        playerEntity.setName(player.getName());
        return playerRepository.save(playerEntity);
    }
}
