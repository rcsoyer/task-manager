package org.acme.taskmanager.domain;

import java.util.ArrayDeque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static java.util.List.of;
import static org.acme.taskmanager.domain.Process.Priority.HIGH;
import static org.acme.taskmanager.domain.Process.Priority.LOW;
import static org.acme.taskmanager.domain.Process.Priority.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(Lifecycle.PER_CLASS)
class ProcessManagerPriorityBasedTest {

    private static final int DEFAULT_MAX_SIZE_PROCESSES = 5;

    private ProcessManagerPriorityBased processManager;

    @BeforeEach
    void setUp() {
        processManager = new ProcessManagerPriorityBased();
    }

    @Nested
    class Add {

        @Test
        void add_whenHasNoSpaceThenOldestLowestPriorityIsRemoved() {
            final var tobeRemoved = new Process(LOW);
            final var processes = new ArrayDeque<>(of(new Process(HIGH),
                                                      new Process(MEDIUM),
                                                      tobeRemoved,
                                                      new Process(HIGH),
                                                      new Process(MEDIUM)));
            final var addition = new Process(MEDIUM);

            processes.forEach(processManager::add);

            assertThat(processManager.getManagedProcesses())
              .hasSize(DEFAULT_MAX_SIZE_PROCESSES);

            assertTrue(processManager.add(addition));

            assertThat(processManager.getManagedProcesses())
              .contains(addition)
              .doesNotContain(tobeRemoved)
              .hasSize(DEFAULT_MAX_SIZE_PROCESSES);
        }

        @Test
        void add_whenHasSpaceThenOldestNotRemoved() {
            final var first = new Process(MEDIUM);
            final var last = new Process(LOW);

            assertTrue(processManager.add(first));
            assertTrue(processManager.add(last));

            assertThat(processManager.getManagedProcesses())
              .hasSizeLessThan(DEFAULT_MAX_SIZE_PROCESSES);

            assertThat(processManager.getManagedProcesses())
              .containsExactly(first, last)
              .hasSizeLessThan(DEFAULT_MAX_SIZE_PROCESSES);
        }
    }
}