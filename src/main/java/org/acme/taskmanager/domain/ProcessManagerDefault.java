package org.acme.taskmanager.domain;

import java.util.ArrayDeque;

public final class ProcessManagerDefault extends ProcessManagerBase implements ProcessManager {

    private static final int MAX_NUMBER_PROCESSES = 5;

    public ProcessManagerDefault() {
        super(new ArrayDeque<>(MAX_NUMBER_PROCESSES));
    }

    @Override
    public boolean addProcess(final Process process) {
        if (processes.size() < MAX_NUMBER_PROCESSES) {
            return processes.add(process);
        }

        return false;
    }
}
