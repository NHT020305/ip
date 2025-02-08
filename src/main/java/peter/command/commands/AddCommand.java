package peter.command.commands;

import peter.command.Command;
import peter.exception.RepeatedTaskException;
import peter.storage.TaskStorage;
import peter.task.Task;
import peter.task.TaskManager;
import peter.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    /**
     * The task to be added.
     */
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command.
     *
     * @param ui          The user interface to display messages.
     * @param taskManager The manager handling tasks.
     * @param taskStorage The storage to save tasks.
     * @throws RepeatedTaskException If the task already exists.
     */
    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) throws RepeatedTaskException {
        taskManager.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task);
        String isMany = taskManager.countTasks() > 1 ? "s" : "";
        System.out.println(" Now you have " + taskManager.countTasks() + " task" + isMany + " in the list.");
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
