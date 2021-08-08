package org.acme.taskmanager.domain;

import java.util.Deque;

public class ProcessManagerPriorityBase extends ProcessManagerBase implements ProcessManager {

    public ProcessManagerPriorityBase(final Deque<Process> processes) {
        super(processes);
    }

    @Override
    public boolean addProcess(final Process process) {
        return false;
    }
}
