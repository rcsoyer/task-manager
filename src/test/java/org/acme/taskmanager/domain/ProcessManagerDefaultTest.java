package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static java.util.Comparator.comparing;
import static java.util.List.of;
import static java.util.UUID.randomUUID;
import static org.acme.taskmanager.domain.Priority.HIGH;
import static org.acme.taskmanager.domain.Priority.LOW;
import static org.acme.taskmanager.domain.Priority.MEDIUM;
import static org.acme.taskmanager.domain.SortProcesses.PID;
import static org.acme.taskmanager.domain.SortProcesses.PRIORITY;
import static org.acme.taskmanager.domain.SortProcesses.TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(Lifecycle.PER_CLASS)
class ProcessManagerDefaultTest {

    private static final int DEFAULT_MAX_SIZE_PROCESSES = 5;

    private ProcessManagerDefault processManager;
    private Queue<Process> processes;

    @BeforeEach
    void setUp() {
        processes = new ArrayDeque<>(of(new Process(randomUUID(), HIGH),
                                        new Process(randomUUID(), MEDIUM),
                                        new Process(randomUUID(), LOW)));

        processManager = new ProcessManagerDefault();

        processes.forEach(processManager::add);
    }

    @Nested
    class ListProcesses {

        @Test
        void listProcesses_byPID() {
            final Collection<Process> orderedByPID = processManager.listSorted(PID);

            assertThat(List.copyOf(orderedByPID))
              .isSortedAccordingTo(comparing(Process::pid));
        }

        @Test
        void listProcesses_byTime() {
            final Collection<Process> orderedByTime = processManager.listSorted(TIME);

            assertThat(orderedByTime)
              .containsExactlyElementsOf(processes);
        }

        @Test
        void listProcesses_byPriority() {
            final Collection<Process> orderedByPriority = processManager.listSorted(PRIORITY);

            assertThat(List.copyOf(orderedByPriority))
              .isSortedAccordingTo(comparing(Process::priority));
        }
    }

    @Test
    void killProcess() {
        final Process head = processManager.getManagedProcesses().iterator().next();
        processManager.kill(head);

        assertThat(processManager.getManagedProcesses()).doesNotContain(head);
    }

    @Test
    void killAllProcessesBy() {
        final var priority = MEDIUM;
        final Collection<Process> beforeKill = processManager.getManagedProcesses();
        final var beforeKillSize = beforeKill.size();

        assertThat(beforeKill)
          .hasSize(beforeKillSize)
          .anyMatch(process -> process.priority() == priority);

        processManager.killAllProcessesBy(priority);

        assertThat(processManager.getManagedProcesses())
          .hasSizeLessThan(beforeKillSize)
          .noneMatch(process -> process.priority() == priority);
    }

    @Test
    void killAllProcesses() {
        assertThat(processManager.getManagedProcesses())
          .isNotEmpty();

        processManager.killAllProcesses();

        assertThat(processManager.getManagedProcesses())
          .isEmpty();
    }

    @Test
    @SuppressWarnings({"OptionalGetWithoutIsPresent", "java:S2699"})
    void killOldestProcess() {
        final Process oldest = processManager.getManagedProcesses().stream().findFirst().get();

        processManager.killOldestProcess();

        assertThat(processManager.getManagedProcesses())
          .doesNotContain(oldest);
    }

    @Nested
    class Add {

        @Test
        void add_whenHasSpaceThenAdded() {
            final var addition = new Process(randomUUID(), MEDIUM);

            assertTrue(processManager.add(addition));

            assertThat(processManager.getManagedProcesses())
              .contains(addition);
        }

        @Test
        void add_whenDoesNotHaveSpaceThenNotAdded() {
            addProcessesUntilMax();

            assertThat(processManager.getManagedProcesses())
              .hasSize(DEFAULT_MAX_SIZE_PROCESSES);

            final var addition = new Process(randomUUID(), HIGH);

            assertFalse(processManager.add(addition));

            assertThat(processManager.getManagedProcesses())
              .doesNotContain(addition)
              .hasSize(DEFAULT_MAX_SIZE_PROCESSES);
        }
    }

    @Nested
    class IsCapacityReached {

        @Test
        void isCapacityReached_true() {
            addProcessesUntilMax();

            assertThat(processManager.getManagedProcesses())
              .hasSize(DEFAULT_MAX_SIZE_PROCESSES);

            assertTrue(processManager.isCapacityReached());
        }

        @Test
        void isCapacityReached_false() {
            assertThat(processManager.getManagedProcesses())
              .hasSizeLessThan(DEFAULT_MAX_SIZE_PROCESSES);

            assertFalse(processManager.isCapacityReached());
        }
    }

    @Test
    void getManagedProcesses() {
        final Collection<Process> managedProcesses = processManager.getManagedProcesses();

        assertThat(managedProcesses)
          .containsExactlyElementsOf(processManager.getManagedProcesses())
          .isNotSameAs(processManager.getManagedProcesses());

        assertThrows(UnsupportedOperationException.class, managedProcesses::clear);
    }

    private void addProcessesUntilMax() {
        while (processManager.getManagedProcesses().size() != DEFAULT_MAX_SIZE_PROCESSES) {
            processManager.add(new Process(randomUUID(), MEDIUM));
        }
    }
}