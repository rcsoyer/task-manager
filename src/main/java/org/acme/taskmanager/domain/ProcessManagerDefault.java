package org.acme.taskmanager.domain;

public final class ProcessManagerDefault extends AbstractProcessManager implements ProcessManager {

    @Override
    public boolean addProcess(final Process process) {
        if (processes.size() < MAX_NUMBER_PROCESSES) {
            return processes.add(process);
        }

        return false;
    }
}
