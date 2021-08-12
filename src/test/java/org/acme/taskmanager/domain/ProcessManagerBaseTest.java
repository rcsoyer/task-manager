package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static java.util.Comparator.comparing;
import static org.acme.taskmanager.domain.SortProcesses.PID;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(Lifecycle.PER_CLASS)
class ProcessManagerBaseTest {

    private ProcessManager processManager;

    @BeforeAll
    void setUp() {
        final var processes = new ArrayDeque<Process>(3);
        processes.add(new Process(UUID.randomUUID(), Priority.HIGH));
        processes.add(new Process(UUID.randomUUID(), Priority.MEDIUM));
        processes.add(new Process(UUID.randomUUID(), Priority.LOW));
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
            final Collection<Process> processes = processManager.listProcesses(PID);
            processes.forEach(process -> System.out.println("process: " + process));

            assertThat(List.copyOf(processes))
              .isSortedAccordingTo(comparing(Process::pid));
        }
    }

    @Test
    void killProcess() {
    }

    @Test
    void killAllProcessesBy() {
    }

    @Test
    void killAllProcesses() {
    }
}