package Peter.task.type;

import Peter.task.Task;

/**
 * Represents a task without a specific date or time constraint.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
