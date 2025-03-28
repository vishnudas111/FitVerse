package com.fitverse.service;


import com.flipfit.entity.Center;
import com.flipfit.repository.CenterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class CenterRepositoryIntegrationTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private CenterRepository centerRepository;

    @Test
    void whenSaved_thenFindByCity() {
        Center center = new Center("1", "Test Center", "Bangalore", List.of("Sunday"));
        centerRepository.save(center);

        List<Center> found = centerRepository.findByCity("Bangalore");

        assertFalse(found.isEmpty());
        assertEquals("Bangalore", found.get(0).getCity());
    }
}