package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.Task;
import peter.task.TaskManager;
import peter.ui.Ui;
import peter.utils.ReplyMessage;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to be deleted.
     */
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete task command.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public String execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        Task task = taskManager.delete(index);
        taskStorage.saveTasks(taskManager);
        if (taskManager.countTasks() == 0) {
            return ReplyMessage.DELETE_ZERO_MESSAGE;
        } else {
            String isMany = taskManager.countTasks() > 1 ? "s" : "";
            return String.format(ReplyMessage.DELETE_MESSAGE, task, taskManager.countTasks(), isMany);
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
