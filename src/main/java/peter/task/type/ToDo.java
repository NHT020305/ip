package peter.task.type;

import java.time.LocalDateTime;

import peter.task.Task;
import peter.utils.TaskKeyword;

/**
 * Represents a task without a specific date or time constraint.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDoTask with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Checks if the ToDoTask is equal to another ToDoTask.
     *
     * @return true if 2 todos have the same description, false otherwise.
     */
    public boolean equals(Task task) {
        if (task instanceof ToDo other) {
            return this.description.equals(other.description);
        }
        return false;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateTimeBy(LocalDateTime by) {
    }

    public void updateTimeFrom(LocalDateTime from) {
    }

    public void updateTimeTo(LocalDateTime by) {
    }

    /**
     * Returns a string representation of the ToDoTask.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return TaskKeyword.TODO_TAG + super.toString();
    }
}
