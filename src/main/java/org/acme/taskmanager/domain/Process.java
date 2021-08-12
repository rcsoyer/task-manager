package org.acme.taskmanager.domain;

import java.util.UUID;

public record Process(UUID pid, Priority priority) {

    public boolean isHigher(final Process other) {
        return priority.isHigher(other.priority());
    }
}
