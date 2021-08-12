package org.acme.taskmanager.domain;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessTest {

    private Process process;

    @BeforeEach
    void setUp() {
        process = new Process(UUID.randomUUID(), Priority.HIGH);
    }

    @Test
    void isHigher() {
        new Process(UUID.randomUUID(), Priority.LOW);
    }
}