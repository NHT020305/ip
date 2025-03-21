package peter.task;

import java.time.LocalDateTime;

import peter.utils.TaskKeyword;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the name description the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is completed, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? TaskKeyword.MARK_DONE : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Checks if the task is equal to another task.
     *
     * @return true if 2 tasks have the same description, false otherwise.
     */
    public abstract boolean equals(Task task);

    public boolean isDone() {
        return isDone;
    }

    public abstract void updateDescription(String description);
    public abstract void updateTimeBy(LocalDateTime time);
    public abstract void updateTimeFrom(LocalDateTime time);
    public abstract void updateTimeTo(LocalDateTime time);

    /**
     * Returns a string representation of the task, including its type,
     * completion status, and name.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
