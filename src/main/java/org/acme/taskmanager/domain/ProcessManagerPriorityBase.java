package org.acme.taskmanager.domain;

public class ProcessManagerPriorityBase extends AbstractProcessManager implements ProcessManager {

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
