package org.acme.taskmanager.domain;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static java.util.UUID.randomUUID;
import static org.acme.taskmanager.util.ParametersValidationUtils.requireNonNull;

@Getter
@ToString
@EqualsAndHashCode(of = "pid")
public final class Process {

    private final UUID pid;
    private final Priority priority;

    public Process(final Priority priority) {
        requireNonNull(priority, "Missing mandatory data to create a 'Process': Priority");
        this.pid = randomUUID();
        this.priority = priority;
    }

    public boolean isHigher(final Process other) {
        requireNonNull(other, "Required 'Process' to be checked was not passed");
        return priority.isHigher(other.getPriority());
    }
}
