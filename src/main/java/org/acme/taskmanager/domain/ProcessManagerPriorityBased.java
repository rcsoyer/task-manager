package org.acme.taskmanager.domain;

public final class ProcessManagerPriorityBased extends ProcessManagerDefault {

    @Override
    public boolean add(final Process addition) {
        if (isCapacityReached()) {
            getManagedProcesses()
              .stream()
              .filter(addition::isHigher)
              .findFirst()
              .ifPresent(this::kill);
        }

        return super.add(addition);
    }
}
