package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        if (taskManager.countTasks() == 0) {
            System.out.println(" There are no tasks in this list.");
            System.out.println(" Let's create a new task!!!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            taskManager.list();
        }
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
