package org.acme.taskmanager.domain;

import java.util.Collection;

public interface ProcessManager {

    boolean addProcess(Process process);

    Collection<Process> listProcesses(SortProcesses sortProcesses);

    boolean killProcess(Process process);

    boolean killAllProcessesBy(Priority priority);

    void killAllProcesses();
}
