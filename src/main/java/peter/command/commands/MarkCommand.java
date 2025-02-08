package peter.command.commands;

import peter.command.Command;
import peter.storage.TaskStorage;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    /**
     * The index of the task to be marked as done.
     */
    private final int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark task command.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     */
    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) {
        taskManager.markAsDone(index);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskManager.getTask(index));
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
