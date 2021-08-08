package org.acme.taskmanager.domain;

import java.util.UUID;

public record Process(UUID pid, Priority priority) {

    /**
     * Terminate a process
     */
    void kill() {

    }

    public boolean isHigher(final Process checkHigher) {
        return checkHigher.priority().isHigher(this.priority);
    }
}
