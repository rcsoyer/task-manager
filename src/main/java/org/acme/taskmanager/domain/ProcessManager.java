package org.acme.taskmanager.domain;

import java.util.UUID;

public interface ProcessManager {

    void addProcess();

    void listProcesses();

    void killProcess(UUID pid);

    void killAllProcessesBy(Priority priority);

    void killAllProcesses();
}
