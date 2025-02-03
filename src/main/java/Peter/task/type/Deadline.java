package Peter.task.type;

import Peter.task.Task;
import java.time.LocalDateTime;

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
     * Returns a string representation of the Deadline task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
