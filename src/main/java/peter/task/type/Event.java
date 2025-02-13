package peter.task.type;

import java.time.LocalDateTime;

import peter.task.Task;
import peter.utils.ReplyMessage;
import peter.utils.TaskKeyword;


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
     * Checks if the event is equal to another event.
     *
     * @return true if 2 tasks have the same description and same time, false otherwise.
     */
    public boolean equals(Task task) {
        if (task instanceof Event other) {
            return this.description.equals(other.description)
                    && this.from.equals(other.from)
                    && this.to.equals(other.to);
        }
        return false;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s%s (from: %s to: %s)", TaskKeyword.EVENT_TAG, super.toString(), from, to);
    }
}
