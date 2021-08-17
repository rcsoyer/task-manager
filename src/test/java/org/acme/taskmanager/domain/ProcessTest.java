package org.acme.taskmanager.domain;

import org.junit.jupiter.api.Test;

import static org.acme.taskmanager.domain.Process.Priority.HIGH;
import static org.acme.taskmanager.domain.Process.Priority.LOW;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessTest {

    @Test
    void isHigher() {
        final var low = new Process(LOW);
        final var high = new Process(HIGH);

        assertTrue(high.isHigher(low));
        assertTrue(high.getPriority().isHigher(low.getPriority()));

        assertFalse(low.isHigher(high));
        assertFalse(low.getPriority().isHigher(high.getPriority()));
    }
}