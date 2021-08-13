package org.acme.taskmanager.domain;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.acme.taskmanager.domain.Priority.HIGH;
import static org.acme.taskmanager.domain.Priority.LOW;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessTest {

    @Test
    void isHigher() {
        final var low = new Process(UUID.randomUUID(), LOW);
        final var high = new Process(UUID.randomUUID(), HIGH);

        assertTrue(high.isHigher(low));
        assertTrue(high.priority().isHigher(low.priority()));

        assertFalse(low.isHigher(high));
        assertFalse(low.priority().isHigher(high.priority()));
    }
}