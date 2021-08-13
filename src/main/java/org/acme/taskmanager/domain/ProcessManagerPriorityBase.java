package org.acme.taskmanager.domain;

import static java.util.Comparator.comparing;

public final class ProcessManagerPriorityBase extends ProcessManagerDefault implements ProcessManager {

    @Override
    public boolean add(final Process process) {
        getManagedProcesses()
          .stream()
          .min(comparing(Process::priority))
          .filter(process::isHigher)
          .ifPresent(this::kill);
        return super.add(process);
    }
}
