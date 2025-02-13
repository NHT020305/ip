package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;
import peter.utils.ReplyMessage;

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
    public String execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        if (taskManager.countTasks() == 0) {
            return ReplyMessage.LIST_ZERO_MESSAGE;
        } else {
            String isMany = taskManager.countTasks() > 1 ? "s" : "";
            return String.format(ReplyMessage.LIST_MESSAGE, taskManager.list(), taskManager.countTasks(), isMany);
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
