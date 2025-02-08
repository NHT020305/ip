package peter.command;

import peter.exception.RepeatedTaskException;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Abstract base class for commands executed in the task management system.
 */
public abstract class Command {

    /**
     * Constructs a Command.
     */
    protected Command() {
    }

    /**
     * Executes the command with the given user interface, task manager, and task storage.
     *
     * @param ui The user interface to interact with the user.
     * @param taskManager The manager for handling task operations.
     * @param taskStorage The storage system for tasks.
     */
    public abstract String execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage)
            throws RepeatedTaskException;

    /**
     * Checks if the command should terminate the program.
     *
     * @return true if the program should terminate, false otherwise.
     */
    public abstract boolean isTerminal();
}
