package org.acme.taskmanager.domain;

import java.util.Collection;

/**
 * An aggregator that manages {@link Process}es. <br/> Implementation classes are expected to define a fixed maximum
 * number of 'processes' that is allowed for a 'manager' to handle
 */
public interface ProcessManager {

    /**
     * Add the given {@link Process} to this {@link ProcessManager} if the fixed maximum number of processes allowed has
     * not been reached yet
     *
     * @return true if the passed {@link Process} was successfully registered with this {@link ProcessManager}
     * @throws IllegalArgumentException if the passed parameter is null
     */
    boolean add(Process process);

    /**
     * Creates an unmodifiable copy of the {@link Process}es registered with this {@link ProcessManager} sorted by the
     * value of {@link Process.Sort}
     *
     * @throws IllegalArgumentException if the passed parameter is null
     */
    Collection<Process> listSorted(Process.Sort sort);

    /**
     * Kill the given {@link Process} if the same is registered with this {@link ProcessManager}
     *
     * @return true if the passed {@link Process} is registered with this {@link ProcessManager} and was successfully
     * removed/killed
     * @throws IllegalArgumentException if the passed parameter is null
     */
    boolean kill(Process process);

    /**
     * Kill all {@link Process}es present in this {@link ProcessManager} that has the given {@link Process.Priority} parameter
     *
     * @return true if the passed {@link Process} is registered with this {@link ProcessManager} and was successfully
     * removed/killed
     * @throws IllegalArgumentException if the passed parameter is null
     */
    boolean killAllProcessesBy(Process.Priority priority);

    void killAllProcesses();
}
