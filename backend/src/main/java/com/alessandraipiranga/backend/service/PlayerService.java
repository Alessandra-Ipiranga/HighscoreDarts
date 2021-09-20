package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.repo.PlayerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
@Getter
@Setter
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
            throw new IllegalArgumentException("Name must not be blank to create user");
        }
        checkUserNameExists(name);

        return playerRepository.save(playerEntity);
    }

    private void checkUserNameExists(String name) {
        Optional<PlayerEntity> existingUser = find(name);
        if (existingUser.isPresent()) {
            throw new EntityExistsException(String.format(
                    "User with name=%s already exists", name));
        }
    }
}
