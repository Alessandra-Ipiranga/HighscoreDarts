package com.alessandraipiranga.backend.model;

import com.alessandraipiranga.backend.SpringBootTests;
import com.alessandraipiranga.backend.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest extends SpringBootTests {

    @Resource
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testCreateUserWithoutNameShouldThrow() {
        try {
            UserEntity userEntity = new UserEntity();
            userRepository.save(userEntity);
            fail("user without name must not be persisted");

        } catch (DataIntegrityViolationException ignore) {
            // expected
        }

    }

    @Test
    @Transactional
    public void testCreateUserName() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("foo");
        userEntity.setPassword("password");
        assertNull(userEntity.getId());

        UserEntity createdEntity = userRepository.save(userEntity);
        assertNotNull(createdEntity.getId());
        assertEquals(createdEntity, userEntity);

        // additional equals hash code test by adding same instance to set twice
        Set<UserEntity> users = new HashSet<>();
        users.add(userEntity);
        users.add(createdEntity);
        assertEquals(1, users.size());
    }
}