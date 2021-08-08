package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ProcessManagerDefault implements ProcessManager {

    private static final int MAX_NUMBER_PROCESSES = 5;

    private final ExecutorService threadPool;
    private final Deque<Process> processes;

    public ProcessManagerDefault() {
        this.threadPool = Executors.newFixedThreadPool(MAX_NUMBER_PROCESSES);
        this.processes = new ArrayDeque<>(MAX_NUMBER_PROCESSES);
    }

    @Override
    public void addProcess() {

    }

    @Override
    public void listProcesses() {

    }

    @Override
    public void killProcess(final UUID pid) {

    }

    @Override
    public void killAllProcessesBy(final Priority priority) {

    }

    @Override
    public void killAllProcesses() {

    }
}
