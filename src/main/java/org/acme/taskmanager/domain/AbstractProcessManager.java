package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Comparator.comparing;

abstract class AbstractProcessManager implements ProcessManager {

    protected static final int MAX_NUMBER_PROCESSES = 5;

    protected final Queue<Process> processes;

    protected AbstractProcessManager() {
        this.processes = new ArrayDeque<>(MAX_NUMBER_PROCESSES);
    }

    @Override
    public Collection<Process> listProcesses(final SortProcesses sorting) {
        return switch (sorting) {
            case TIME -> unmodifiableCollection(processes);
            case PID -> processes.stream()
                                 .sorted(comparing(Process::pid))
                                 .toList();
            case PRIORITY -> processes.stream()
                                      .sorted(comparing(Process::priority))
                                      .toList();
        };
    }

    @Override
    public boolean kill(final Process process) {
        return processes.remove(process);
    }

    @Override
    public boolean killAllProcessesBy(final Priority priority) {
        return processes.removeIf(process -> process.priority() == priority);
    }

    @Override
    public void killAllProcesses() {
        processes.clear();
    }
}
