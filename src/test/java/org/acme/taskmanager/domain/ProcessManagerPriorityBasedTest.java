package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static java.util.List.of;
import static java.util.UUID.randomUUID;
import static org.acme.taskmanager.domain.Priority.HIGH;
import static org.acme.taskmanager.domain.Priority.LOW;
import static org.acme.taskmanager.domain.Priority.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessManagerPriorityBasedTest {

    private static final int DEFAULT_MAX_SIZE_PROCESSES = 5;

    private ProcessManagerPriorityBased processManager;
    private Queue<Process> processes;

    @BeforeEach
    void setUp() {
        processManager = new ProcessManagerPriorityBased();
        processes = new ArrayDeque<>(of(new Process(randomUUID(), HIGH),
                                        new Process(randomUUID(), MEDIUM),
                                        new Process(randomUUID(), LOW),
                                        new Process(randomUUID(), HIGH),
                                        new Process(randomUUID(), MEDIUM)));
        processes.forEach(processManager::add);
    }

    @Nested
    class Add {

        @Test
        void add_whenHasNoSpaceThenOldestLowestPriorityIsRemoved() {
            final var addition = new Process(randomUUID(), MEDIUM);

            assertThat(processManager.getManagedProcesses())
              .hasSize(DEFAULT_MAX_SIZE_PROCESSES);

            assertTrue(processManager.add(addition));

            assertThat(processManager.getManagedProcesses())
              .contains(addition)
              .hasSize(DEFAULT_MAX_SIZE_PROCESSES);
        }
    }
}