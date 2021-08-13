package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Comparator.comparing;

public class ProcessManagerDefault implements ProcessManager {

    private static final int MAX_NUMBER_PROCESSES = 5;

    private final Queue<Process> processes = new ArrayDeque<>(MAX_NUMBER_PROCESSES);

    @Override
    public final Collection<Process> listSorted(final SortProcesses sorting) {
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
    public final boolean kill(final Process process) {
        return processes.remove(process);
    }

    @Override
    public final boolean killAllProcessesBy(final Priority priority) {
        return processes.removeIf(process -> process.priority() == priority);
    }

    @Override
    public final void killAllProcesses() {
        processes.clear();
    }

    @Override
    public boolean add(final Process process) {
        if (!isCapacityReached()) {
            return processes.offer(process);
        }

        return false;
    }

    protected void killOldest() {
        processes.poll();
    }

    protected boolean isCapacityReached() {
        return processes.size() == MAX_NUMBER_PROCESSES;
    }

    protected Collection<Process> getProcesses() {
        return unmodifiableCollection(processes);
    }
}
