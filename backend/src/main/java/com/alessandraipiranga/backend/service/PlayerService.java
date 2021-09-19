package com.alessandraipiranga.backend.service;

import com.alessandraipiranga.backend.model.PlayerEntity;
import com.alessandraipiranga.backend.repo.PlayerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
public class PlayerService {

    private PlayerRepository playerRepository;

    public Optional<PlayerEntity> find(String name) {
        return playerRepository.findByName(name);
    }
}
