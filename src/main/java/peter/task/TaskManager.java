package peter.task;

import java.util.ArrayList;

import peter.exception.RepeatedTaskException;
import peter.utils.ErrorMessage;

/**
 * Manages a list of tasks, providing operations to add, remove, and modify tasks.
 */
public class TaskManager {
    protected ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskManager.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskManager with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the number of tasks.
     *
     * @return The size of the task list.
     */
    public int countTasks() {
        return tasks.size();
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(String.format(ErrorMessage.INVALID_TASK_NUMBER, index + 1));
        }
        return tasks.get(index);
    }

    /**
     * Lists all tasks.
     */
    public String list() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(String.format("   %d. %s%n", i + 1, this.tasks.get(i)));
        }
        return output.toString();
    }

    /**
     * Clears all tasks from the task list.
     */
    public void reset() {
        this.tasks.clear();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) throws RepeatedTaskException {
        for (Task t : tasks) {
            if (t.equals(task)) {
                throw new RepeatedTaskException(ErrorMessage.ALREADY_EXISTS);
            }
        }
        tasks.add(task);
    }

    /**
     * Deletes a task by its index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task delete(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(String.format(ErrorMessage.INVALID_TASK_NUMBER, index + 1));
        }
        return tasks.remove(index);
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index The index of the task to mark as done.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void markAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(String.format(ErrorMessage.INVALID_TASK_NUMBER, index + 1));
        }
        tasks.get(index).markDone();
    }

    /**
     * Marks a task as not done by its index.
     *
     * @param index The index of the task to mark as not done.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void markAsNotDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(String.format(ErrorMessage.INVALID_TASK_NUMBER, index + 1));
        }
        tasks.get(index).markNotDone();
    }

    /**
     * List down all the tasks matching the given keyword.
     *
     * @param keyWord The keyword that user want to look for.
     */
    public ArrayList<Task> search(String keyWord) {
        ArrayList<Task> newTaskList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                newTaskList.add(task);
            }
        }
        return newTaskList;
    }
}
