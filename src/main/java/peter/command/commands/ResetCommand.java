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
    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        taskManager.reset();
        System.out.println(" Got it. Now your task list is empty.");
        System.out.println(" Let's create a new task!!!");
        taskStorage.saveTasks(taskManager);
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
