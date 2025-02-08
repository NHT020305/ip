package peter.task.type;

import java.time.LocalDateTime;

import peter.task.Task;


/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The date and time by which the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Checks if the event is equal to another event.
     *
     * @return true if 2 tasks have the same description and same time, false otherwise.
     */
    public boolean equals(Task task) {
        if (task instanceof Deadline other) {
            return this.description.equals(other.description)
                    && this.by.equals(other.by);
        }
        return false;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
