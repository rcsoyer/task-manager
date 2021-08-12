package org.acme.taskmanager.domain;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;
import static org.acme.taskmanager.domain.SortProcesses.TIME;
import static org.assertj.core.api.Assertions.assertThat;

class ProcessManagerDefaultTest {

    private ProcessManager processManager;

    @BeforeEach
    void setUp() {
        processManager = new ProcessManagerDefault();
    }

    @Test
    void addProcess() {
        final Priority[] priorities = Priority.values();
        final int maxPriorities = priorities.length;
        final var random = new Random();

        final var processes =
          IntStream
            .range(0, 13)
            .mapToObj(priority -> new Process(randomUUID(), priorities[random.nextInt(maxPriorities)]))
            .collect(toList());

        processes.forEach(processManager::addProcess);

        assertThat(processManager.listProcesses(TIME))
          .hasSize(5)
          .isSubsetOf(processes);
    }
}