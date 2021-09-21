package com.alessandraipiranga.backend.model;

import com.alessandraipiranga.backend.SpringBootTests;
import com.alessandraipiranga.backend.repo.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class PlayerEntityTest extends SpringBootTests {

    @Resource
    private PlayerRepository playerRepository;

    @Test
    @Transactional
    public void testCreatePlayerWithoutNameShouldThrow() {
        try {
            PlayerEntity playerEntity = new PlayerEntity();
            playerRepository.save(playerEntity);
            fail("user without name must not be persisted");

        } catch (DataIntegrityViolationException ignore) {
            // expected
        }
    }


}