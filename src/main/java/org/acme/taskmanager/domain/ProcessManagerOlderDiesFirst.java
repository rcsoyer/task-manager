package org.acme.taskmanager.domain;

public final class ProcessManagerOlderDiesFirst extends ProcessManagerDefault {

    @Override
    public boolean add(final Process process) {
        if (isCapacityReached()) {
            getManagedProcesses()
              .stream()
              .findFirst()
              .ifPresent(this::kill);
        }

        return super.add(process);
    }
}
