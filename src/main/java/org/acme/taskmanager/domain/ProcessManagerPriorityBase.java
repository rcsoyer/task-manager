package org.acme.taskmanager.domain;

import java.util.ArrayDeque;

public class ProcessManagerPriorityBase extends ProcessManagerBase implements ProcessManager {

    public ProcessManagerPriorityBase() {
        super(new ArrayDeque<>(MAX_NUMBER_PROCESSES));
    }

    @Override
    public boolean addProcess(final Process process) {
        return processes
          .stream()
          .filter(process::isHigher)
          .findFirst()
          .filter(processes::remove)
          .isPresent();
    }
}
