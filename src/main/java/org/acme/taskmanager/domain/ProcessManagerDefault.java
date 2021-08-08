package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

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
