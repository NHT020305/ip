package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Represents a command to display the number of tasks in the task list.
 */
public class CountCommand extends Command {

    /**
     * Executes the count command, showing the number of tasks in the task list.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public String execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        String output = "";
        if (taskManager.countTasks() == 0) {
            output = """
                     There are no tasks in this list.
                     Let's create a new task!!!
                    """;
        }
        String isMany = taskManager.countTasks() > 1 ? "s" : "";
        output += " You have "
                + taskManager.countTasks()
                + " task"
                + isMany
                + " in the list.";
        taskStorage.saveTasks(taskManager);
        return output;
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
