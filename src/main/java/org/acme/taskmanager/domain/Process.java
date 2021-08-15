package org.acme.taskmanager.domain;

import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.acme.taskmanager.domain.util.ParametersValidationUtils.requireNonNull;

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

    public UUID getPid() {
        return pid;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Process) obj;
        return Objects.equals(this.pid, that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }

    @Override
    public String toString() {
        return "Process[" +
                 "pid=" + pid + ", " +
                 "priority=" + priority + ']';
    }

}
