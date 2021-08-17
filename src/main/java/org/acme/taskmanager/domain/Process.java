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

    /**
     * Verify which process has a higher {@link Priority}
     */
    public boolean isHigher(final Process other) {
        requireNonNull(other, "Required 'Process' to be checked was not passed");
        return priority.isHigher(other.getPriority());
    }

    /**
     * @implNote The declaration order of the Enum values matters for the evaluation of the method {@link
     * Priority#isHigher(Priority)}
     */
    public enum Priority {

        LOW,
        MEDIUM,
        HIGH;

        /**
         * @implNote Uses the {@link Enum#ordinal()} to calculate highers and lowers of the {@link Priority}
         */
        boolean isHigher(final Priority other) {
            return ordinal() > other.ordinal();
        }
    }

}
