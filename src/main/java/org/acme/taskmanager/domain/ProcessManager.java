package org.acme.taskmanager.domain;

import java.util.Collection;

public interface ProcessManager {

    boolean addProcess(Process process);

    Collection<Process> listProcesses();

    void killProcess(Process process);

    void killAllProcessesBy(Priority priority);

    void killAllProcesses();
}
