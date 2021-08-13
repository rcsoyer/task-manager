package org.acme.taskmanager.domain;

import java.util.Collection;

public interface ProcessManager {

    /**
     * Kill the given {@link Process} if the same is registered with this {@link ProcessManager}
     *
     * @return true if the passed {@link Process} was successfully registered with this {@link ProcessManager}
     * @throws NullPointerException if the passed parameter is null
     */
    boolean add(Process process);


    /**
     * Creates an unmodifiable copy of the {@link Process}es registered with this {@link ProcessManager} sorted by the
     * value of {@link SortProcesses}
     *
     * @throws NullPointerException if the passed parameter is null
     */
    Collection<Process> listSorted(SortProcesses sortProcesses);

    /**
     * Kill the given {@link Process} if the same is registered with this {@link ProcessManager}
     *
     * @return true if the passed {@link Process} is registered with this {@link ProcessManager} and was successfully
     * removed/killed
     * @throws NullPointerException if the passed parameter is null
     */
    boolean kill(Process process);


    /**
     * Kill all {@link Process}es present in this {@link ProcessManager} that has the given {@link Priority} parameter
     *
     * @return true if the passed {@link Process} is registered with this {@link ProcessManager} and was successfully
     * removed/killed
     * @throws NullPointerException if the passed parameter is null
     */
    boolean killAllProcessesBy(Priority priority);

    void killAllProcesses();
}
