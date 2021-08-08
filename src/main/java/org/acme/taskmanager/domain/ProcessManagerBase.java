package org.acme.taskmanager.domain;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

abstract class ProcessManagerBase implements ProcessManager {

    protected final Collection<Process> processes;

    protected ProcessManagerBase(final Collection<Process> processes) {
        this.processes = processes;
    }

    @Override
    public Collection<Process> listProcesses() {
        return unmodifiableCollection(processes);
    }

    @Override
    public void killProcess(final Process process) {

    }

    @Override
    public void killAllProcessesBy(final Priority priority) {

    }

    @Override
    public void killAllProcesses() {

    }
}
