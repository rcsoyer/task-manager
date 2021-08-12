package org.acme.taskmanager.domain;

import java.util.Collection;

public interface ProcessManager {

    boolean addProcess(Process process);

    Collection<Process> listProcesses(SortProcesses sortProcesses);

    boolean kill(Process process);

    boolean killAllProcessesBy(Priority priority);

    void killAllProcesses();
}
