package org.acme.taskmanager.domain;

/**
 * @implNote The declaration order of the Enum values matters for the evaluation of the method {@link
 * Priority#isHigher(Priority)}
 */
public enum Priority {

    LOW,
    MEDIUM,
    HIGH;

    boolean isHigher(final Priority other) {
        return ordinal() > other.ordinal();
    }
}
