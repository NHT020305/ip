package Peter.command;

import Peter.task.TaskManager;
import Peter.ui.Ui;
import Peter.storage.TaskStorage;

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
    public abstract void execute(Ui ui, TaskManager taskManager,
                                 TaskStorage taskStorage);

    /**
     * Checks if the command should terminate the program.
     *
     * @return true if the program should terminate, false otherwise.
     */
    public abstract boolean isTerminal();
}
