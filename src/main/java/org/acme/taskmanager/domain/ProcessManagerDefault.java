package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Comparator.comparing;
import static org.acme.taskmanager.util.ParametersValidationUtils.requireNonNull;

public class ProcessManagerDefault implements ProcessManager {

    private static final int MAX_NUMBER_PROCESSES = 5;

    private final Queue<Process> managedProcesses = new ArrayDeque<>(MAX_NUMBER_PROCESSES);

    @Override
    public final Collection<Process> listSorted(final SortProcesses sorting) {
        requireNonNull(sorting, "No 'sorting' filter was informed");
        return switch (sorting) {
            case TIME -> getManagedProcesses();
            case PID -> managedProcesses.stream()
                                        .sorted(comparing(Process::getPid))
                                        .toList();
            case PRIORITY -> managedProcesses.stream()
                                             .sorted(comparing(Process::getPriority))
                                             .toList();
        };
    }

    @Override
    public final boolean kill(final Process process) {
        requireNonNull(process, "No 'Process' was informed");
        return managedProcesses.remove(process);
    }

    @Override
    public final boolean killAllProcessesBy(final Priority filter) {
        requireNonNull(filter, "No 'Priority' filter was informed");
        return managedProcesses.removeIf(process -> process.getPriority() == filter);
    }

    @Override
    public final void killAllProcesses() {
        managedProcesses.clear();
    }

    @Override
    public boolean add(final Process process) {
        requireNonNull(process, "No 'Process' was informed");

        if (!isCapacityReached()) {
            return managedProcesses.offer(process);
        }

        return false;
    }

    /**
     * The max capacity is defined and fixed at the creation of a {@link ProcessManager}
     */
    protected boolean isCapacityReached() {
        return managedProcesses.size() == MAX_NUMBER_PROCESSES;
    }

    /**
     * Creates an unmodifiable copy of the underlying {@link ProcessManagerDefault#managedProcesses}. <br/> By default
     * it's always ordered by age. This is, the older are the ones that were added first.
     */
    protected Collection<Process> getManagedProcesses() {
        return unmodifiableCollection(managedProcesses);
    }
}
