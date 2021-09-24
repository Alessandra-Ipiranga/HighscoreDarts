package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<PlayerEntity> find(String name) {
        return playerRepository.findByName(name);
    }

    public PlayerEntity create(PlayerEntity playerEntity) {
        String name = playerEntity.getName();
        if (!hasText(name)) {
            throw new IllegalArgumentException("Name must not be blank to create Player");
        }
        checkPlayerNameExists(name);

        return playerRepository.save(playerEntity);
    }

    private void checkPlayerNameExists(String name) {
        Optional<PlayerEntity> existingUser = find(name);
        if (existingUser.isPresent()) {
            throw new EntityExistsException(String.format(
                    "Player with name=%s already exists", name));
        }
    }

    public PlayerEntity findPlayer(String name) {
        Optional<PlayerEntity> playerEntityOptional = playerRepository.findByName(name);
        if(playerEntityOptional.isEmpty()){
            throw new EntityNotFoundException("Player not found");
        } return playerEntityOptional.get();
    }

    public List<PlayerEntity> findAllPlayer() {
        return playerRepository.findAll();
    }

    public Optional<PlayerEntity> delete(String name) {
        Optional<PlayerEntity> playerEntityOptional = find(name);
        if (playerEntityOptional.isPresent()) {
            PlayerEntity userEntity = playerEntityOptional.get();
            playerRepository.delete(userEntity);
        }
        return playerEntityOptional;
    }
}
