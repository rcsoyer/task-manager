package org.acme.taskmanager.domain;

public enum Priority {

    LOW,
    MEDIUM,
    HIGH;

    boolean isHigher(final Priority priority) {
        return ordinal() > priority.ordinal();
    }
}
