package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Comparator.comparing;

public class ProcessManagerDefault implements ProcessManager {

    private static final int MAX_NUMBER_PROCESSES = 5;

    private final Queue<Process> managedProcesses = new ArrayDeque<>(MAX_NUMBER_PROCESSES);

    @Override
    public final Collection<Process> listSorted(final SortProcesses sorting) {
        return switch (sorting) {
            case TIME -> unmodifiableCollection(managedProcesses);
            case PID -> managedProcesses.stream()
                                        .sorted(comparing(Process::pid))
                                        .toList();
            case PRIORITY -> managedProcesses.stream()
                                             .sorted(comparing(Process::priority))
                                             .toList();
        };
    }

    @Override
    public final boolean kill(final Process process) {
        return managedProcesses.remove(process);
    }

    @Override
    public final boolean killAllProcessesBy(final Priority priority) {
        return managedProcesses.removeIf(process -> process.priority() == priority);
    }

    @Override
    public final void killAllProcesses() {
        managedProcesses.clear();
    }

    @Override
    public boolean add(final Process process) {
        if (!isCapacityReached()) {
            return managedProcesses.offer(process);
        }

        return false;
    }

    protected void killOldest() {
        managedProcesses.poll();
    }

    protected boolean isCapacityReached() {
        return managedProcesses.size() == MAX_NUMBER_PROCESSES;
    }

    protected Collection<Process> getManagedProcesses() {
        return unmodifiableCollection(managedProcesses);
    }
}
