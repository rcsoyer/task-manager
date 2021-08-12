package org.acme.taskmanager.domain;

public class ProcessManagerOlderDiesFirst extends ProcessManagerBase implements ProcessManager {

    @Override
    public boolean addProcess(final Process process) {
        if (processes.size() == MAX_NUMBER_PROCESSES) {
            processes.poll();
            return processes.add(process);
        }

        return false;
    }
}
