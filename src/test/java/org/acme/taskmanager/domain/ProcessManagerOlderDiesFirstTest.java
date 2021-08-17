package org.acme.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.acme.taskmanager.domain.Process.Priority.HIGH;
import static org.acme.taskmanager.domain.Process.Priority.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessManagerOlderDiesFirstTest {

    private static final int DEFAULT_MAX_SIZE_PROCESSES = 5;

    private ProcessManagerOlderDiesFirst processManager;

    @BeforeEach
    void setUp() {
        processManager = new ProcessManagerOlderDiesFirst();
    }

    @Test
    void add_whenHasSpaceThenOldestNotRemoved() {
        final var first = new Process(HIGH);

        processManager.add(first);

        assertThat(processManager.getManagedProcesses())
          .hasSizeLessThan(DEFAULT_MAX_SIZE_PROCESSES);

        final var last = new Process(MEDIUM);

        assertTrue(processManager.add(last));

        assertThat(processManager.getManagedProcesses())
          .containsExactly(first, last);
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void add_whenHasNoSpaceThenOldestIsRemoved() {
        addProcessesUntilMax();

        assertThat(processManager.getManagedProcesses())
          .hasSize(DEFAULT_MAX_SIZE_PROCESSES);

        final Process oldest = processManager.getManagedProcesses().stream().findFirst().get();
        final var addition = new Process(HIGH);

        assertTrue(processManager.add(addition));

        assertThat(processManager.getManagedProcesses())
          .hasSize(DEFAULT_MAX_SIZE_PROCESSES)
          .contains(addition)
          .doesNotContain(oldest);
    }

    private void addProcessesUntilMax() {
        while (processManager.getManagedProcesses().size() != DEFAULT_MAX_SIZE_PROCESSES) {
            processManager.add(new Process(HIGH));
        }
    }
}