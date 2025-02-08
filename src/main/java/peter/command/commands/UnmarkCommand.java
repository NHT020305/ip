package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    /**
     * The index of the task to be marked as not done.
     */
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark task command.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public String execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        taskManager.markAsNotDone(index);
        taskStorage.saveTasks(taskManager);
        return " OK, I've marked this task as not done yet:"
                + "  " + taskManager.getTask(index);
    }

    /**
     * Indicates whether this command is terminal, i.e., should terminate the program.
     *
     * @return {@code false}, since this command does not terminate the program.
     */
    public boolean isTerminal() {
        return false;
    }
}
