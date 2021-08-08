package org.acme.taskmanager.domain;

import java.util.Collection;
import java.util.Queue;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Comparator.comparing;

abstract class ProcessManagerBase implements ProcessManager {

    protected static final int MAX_NUMBER_PROCESSES = 5;

    protected final Queue<Process> processes;

    protected ProcessManagerBase(final Queue<Process> processes) {
        this.processes = processes;
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
    public boolean killProcess(final Process process) {
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
