package Peter.command.commands;

import Peter.command.Command;
import Peter.exception.RepeatedTaskException;
import Peter.storage.TaskStorage;
import Peter.task.Task;
import Peter.task.TaskManager;
import Peter.ui.Ui;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(Ui ui, TaskManager taskManager, TaskStorage taskStorage) throws RepeatedTaskException {
        taskManager.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task);
        String isMany = taskManager.size() > 1 ? "s" : "";
        System.out.println(" Now you have " + taskManager.size() +
                " task" + isMany + " in the list.");
        taskStorage.saveTasks(taskManager);
    }

    public boolean isTerminal() {
        return false;
    }
}
