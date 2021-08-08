package org.acme.taskmanager.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

import static java.util.Collections.unmodifiableCollection;
import static org.acme.taskmanager.domain.SortProcesses.TIME;

abstract class ProcessManagerBase implements ProcessManager {

    protected final Deque<Process> processes;

    protected ProcessManagerBase(final Deque<Process> processes) {
        this.processes = processes;
    }

    @Override
    public Collection<Process> listProcesses(final SortProcesses sortProcesses) {
        if (sortProcesses == TIME) {
            final var sorted = new ArrayList<Process>(processes.size());
            processes.descendingIterator().forEachRemaining(sorted::add);
            return unmodifiableCollection(sorted);
        }

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
