package org.acme.taskmanager.domain;

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
