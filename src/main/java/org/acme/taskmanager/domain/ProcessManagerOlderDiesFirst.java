package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Deque;

public class ProcessManagerOlderDiesFirst extends ProcessManagerBase implements ProcessManager {

    public ProcessManagerOlderDiesFirst(final Deque<Process> processes) {
        super(new ArrayDeque<>(MAX_NUMBER_PROCESSES));
    }

    @Override
    public boolean addProcess(final Process process) {
        if (processes.size() == MAX_NUMBER_PROCESSES) {
            processes.removeLast();
            return true;
        }

        return false;
    }
}
