package org.acme.taskmanager.domain;

import java.util.Collection;

public interface ProcessManager {

    boolean add(Process process);

    Collection<Process> listSorted(SortProcesses sortProcesses);

    boolean kill(Process process);

    boolean killAllProcessesBy(Priority priority);

    void killAllProcesses();
}
