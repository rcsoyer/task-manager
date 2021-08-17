package org.acme.taskmanager.domain;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Comparator.comparing;
import static org.acme.taskmanager.util.ParametersValidationUtils.requireNonNull;

public class ProcessManagerDefault implements ProcessManager {

    private static final int MAX_NUMBER_PROCESSES = 5;

    /**
     * @implNote The time of addition is a desirable feature that is automatically sorted by age and that must not
     * change. Hence, in here a {@link Queue} is used instead of a different type of {@link Collection}. <br/>Picking up
     * {@link ArrayBlockingQueue} for the convenience of handling parallel requests. <br/> If parallel requests are not
     * a requirement the class implementation of {@link Queue} can be changed
     */
    private final Queue<Process> managedProcesses = new ArrayBlockingQueue<>(MAX_NUMBER_PROCESSES);

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
    public final boolean killAllProcessesBy(final Process.Priority filter) {
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
