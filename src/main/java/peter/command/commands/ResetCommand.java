package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Represents a command to reset the task list by deleting all tasks.
 */
public class ResetCommand extends Command {

    /**
     * Executes the reset command, clearing all tasks.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public String execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        taskManager.reset();
        taskStorage.saveTasks(taskManager);
        return "Got it. Now your task list is empty.\n"
                + "Let's create a new task!!!";
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
