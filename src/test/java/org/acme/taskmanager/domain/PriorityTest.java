package org.acme.taskmanager.domain;

import org.junit.jupiter.api.Test;

import static org.acme.taskmanager.domain.Process.Priority.HIGH;
import static org.acme.taskmanager.domain.Process.Priority.LOW;
import static org.acme.taskmanager.domain.Process.Priority.MEDIUM;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriorityTest {

    @Test
    void isHigher_Low() {

        assertFalse(LOW.isHigher(HIGH));
        assertFalse(LOW.isHigher(MEDIUM));
        assertFalse(LOW.isHigher(LOW));
    }

    @Test
    void isHigher_Medium() {

        assertFalse(MEDIUM.isHigher(HIGH));
        assertTrue(MEDIUM.isHigher(LOW));
        assertFalse(MEDIUM.isHigher(MEDIUM));
    }

    @Test
    void isHigher_High() {

        assertTrue(HIGH.isHigher(LOW));
        assertTrue(HIGH.isHigher(MEDIUM));
        assertFalse(HIGH.isHigher(HIGH));
    }
}