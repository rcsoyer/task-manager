package org.acme.taskmanager.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;

import static java.util.Collections.unmodifiableCollection;

abstract class ProcessManagerBase implements ProcessManager {

    protected final Deque<Process> processes;

    protected ProcessManagerBase(final Deque<Process> processes) {
        this.processes = processes;
    }

    @Override
    public Collection<Process> listProcesses(final SortProcesses sortProcesses) {
        return switch (sortProcesses) {
            case TIME -> {
                final var sorted = new ArrayList<Process>(processes.size());
                processes.descendingIterator().forEachRemaining(sorted::add);
                yield unmodifiableCollection(sorted);
            }
            case PID -> processes.stream()
                                 .sorted(Comparator.comparing(Process::pid))
                                 .toList();
            case PRIORITY -> processes.stream()
                                      .sorted(Comparator.comparing(Process::priority))
                                      .toList();
        };
    }

    @Override
    public void killProcess(final Process process) {

    }

    @Override
    public void killAllProcessesBy(final Priority priority) {

    }

    @Override
    public void killAllProcesses() {

    }
}
