package org.acme.taskmanager.domain;

public final class ProcessManagerOlderDiesFirst extends ProcessManagerDefault implements ProcessManager {

    @Override
    public boolean add(final Process process) {
        if (isCapacityReached()) {
            killOldestProcess();
        }

        return super.add(process);
    }
}
