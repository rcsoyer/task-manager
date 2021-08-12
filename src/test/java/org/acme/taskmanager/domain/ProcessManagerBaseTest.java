package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.BeforeAll;
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
class ProcessManagerBaseTest {

    private ProcessManager processManager;
    private Queue<Process> processes;

    @BeforeAll
    void setUp() {
        processes = new ArrayDeque<>(3);
        processes.add(new Process(randomUUID(), HIGH));
        processes.add(new Process(randomUUID(), MEDIUM));
        processes.add(new Process(randomUUID(), LOW));

        processManager = new ProcessManagerBase(processes) {
            @Override
            public boolean addProcess(final Process process) {
                return false;
            }
        };
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
              .isNotSameAs(processes)
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
        final Process head = processes.element();
        processManager.kill(head);

        assertThat(processes).doesNotContain(head);
    }

    @Test
    void killAllProcessesBy() {
    }

    @Test
    void killAllProcesses() {
    }
}