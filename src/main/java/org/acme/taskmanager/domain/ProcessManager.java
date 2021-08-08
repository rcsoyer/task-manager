package org.acme.taskmanager.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ProcessManager {

    private static final int MAX_NUMBER_PROCESSES = 5;

    private final ExecutorService threadPool;
    private final Deque<Process> processes;

    public ProcessManager() {
        this.threadPool = Executors.newFixedThreadPool(MAX_NUMBER_PROCESSES);
        this.processes = new ArrayDeque<>(MAX_NUMBER_PROCESSES);
    }
}
