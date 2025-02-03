package Peter.task.type;

import Peter.task.Task;
import java.time.LocalDateTime;

/**
 * Represents a task that occurs within a specific time frame.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description and time frame.
     *
     * @param description The description of the task.
     * @param from The starting date and time of the event.
     * @param to The ending date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
