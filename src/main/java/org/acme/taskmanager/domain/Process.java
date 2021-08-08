package org.acme.taskmanager.domain;

import java.util.UUID;

public record Process(UUID pid, Priority priority) {

    /**
     * Terminate a process
     */
    void kill() {

    }
}
