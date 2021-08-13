package org.acme.taskmanager.domain;

import java.util.UUID;

import static org.acme.taskmanager.domain.util.ParametersValidationUtils.requireNonNull;

public record Process(UUID pid, Priority priority) {

    public Process {
        requireNonNull(pid, "Missing mandatory data to create a 'Process': PID");
        requireNonNull(priority, "Missing mandatory data to create a 'Process': Priority");
    }

    public boolean isHigher(final Process other) {
        requireNonNull(other, "Required 'Process' to be checked was not passed");
        return priority.isHigher(other.priority());
    }
}
