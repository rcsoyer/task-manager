package org.acme.taskmanager.domain;

public enum Priority {

    LOW,
    MEDIUM,
    HIGH;

    public boolean isHigher(final Priority priority) {
        return ordinal() > priority.ordinal();
    }
}
