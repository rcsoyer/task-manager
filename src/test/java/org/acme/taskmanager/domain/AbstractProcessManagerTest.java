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
import static java.util.UUID.randomUUID;
import static org.acme.taskmanager.domain.Priority.HIGH;
import static org.acme.taskmanager.domain.Priority.LOW;
import static org.acme.taskmanager.domain.Priority.MEDIUM;
import static org.acme.taskmanager.domain.SortProcesses.PID;
import static org.acme.taskmanager.domain.SortProcesses.PRIORITY;
import static org.acme.taskmanager.domain.SortProcesses.TIME;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(Lifecycle.PER_CLASS)
class AbstractProcessManagerTest {

    private ProcessManager processManager;
    private Queue<Process> processes;

    @BeforeEach
    void setUp() {
        processes = new ArrayDeque<>(3);
        processes.add(new Process(randomUUID(), HIGH));
        processes.add(new Process(randomUUID(), MEDIUM));
        processes.add(new Process(randomUUID(), LOW));

        processManager = new AbstractProcessManager() {
            @Override
            public boolean addProcess(final Process process) {
                return this.processes.add(process);
            }
        };

        processes.forEach(processManager::addProcess);
    }

    @Nested
    class ListProcesses {

        @Test
        void listProcesses_byPID() {
            final Collection<Process> orderedByPID = processManager.listProcesses(PID);

            assertThat(List.copyOf(orderedByPID))
              .isSortedAccordingTo(comparing(Process::pid));
        }

        @Test
        void listProcesses_byTime() {
            final Collection<Process> orderedByTime = processManager.listProcesses(TIME);

            assertThat(orderedByTime)
              .containsExactlyElementsOf(processes);
        }

        @Test
        void listProcesses_byPriority() {
            final Collection<Process> orderedByPriority = processManager.listProcesses(PRIORITY);

            assertThat(List.copyOf(orderedByPriority))
              .isSortedAccordingTo(comparing(Process::priority));
        }
    }

    @Test
    void killProcess() {
        final Process head = processManager.listProcesses(TIME).iterator().next();
        processManager.kill(head);

        assertThat(processManager.listProcesses(TIME)).doesNotContain(head);
    }

    @Test
    void killAllProcessesBy() {
        final var priority = MEDIUM;

        assertThat(processManager.listProcesses(PID))
          .hasSize(3)
          .anyMatch(process -> process.priority() == priority);

        processManager.killAllProcessesBy(priority);

        assertThat(processManager.listProcesses(PID))
          .hasSize(2)
          .noneMatch(process -> process.priority() == priority);
    }

    @Test
    void killAllProcesses() {
        assertThat(processManager.listProcesses(PRIORITY))
          .hasSize(3);

        processManager.killAllProcesses();

        assertThat(processManager.listProcesses(PRIORITY)).isEmpty();
    }
}