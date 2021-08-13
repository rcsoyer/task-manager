package org.acme.taskmanager.domain;

public enum Priority {

    LOW,
    MEDIUM,
    HIGH;

    boolean isHigher(final Priority other) {
        return ordinal() > other.ordinal();
    }
}
